package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.NoisyOr$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.NoisyOr.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var12$stateProbabilityGlobal;
		double[] cv$var15$stateProbabilityGlobal;
		double[] cv$var18$stateProbabilityGlobal;
		double[][] cv$var225$stateProbabilityGlobal;
		double[][] cv$var238$stateProbabilityGlobal;
		double[][] cv$var251$stateProbabilityGlobal;
		double[][] cv$var264$stateProbabilityGlobal;
		double[][] cv$var277$stateProbabilityGlobal;
		double[][] cv$var290$stateProbabilityGlobal;
		double[] cv$var3$stateProbabilityGlobal;
		double[] cv$var6$stateProbabilityGlobal;
		double[] cv$var9$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var3$stateProbabilityGlobal = new double[2];
			cv$var6$stateProbabilityGlobal = new double[2];
			cv$var9$stateProbabilityGlobal = new double[2];
			cv$var12$stateProbabilityGlobal = new double[2];
			cv$var15$stateProbabilityGlobal = new double[2];
			cv$var18$stateProbabilityGlobal = new double[2];
			{
				int cv$threadCount = threadCount();
				cv$var225$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var225$stateProbabilityGlobal[cv$index] = new double[2];
			}
			{
				int cv$threadCount = threadCount();
				cv$var238$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var238$stateProbabilityGlobal[cv$index] = new double[2];
			}
			{
				int cv$threadCount = threadCount();
				cv$var251$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var251$stateProbabilityGlobal[cv$index] = new double[2];
			}
			{
				int cv$threadCount = threadCount();
				cv$var264$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var264$stateProbabilityGlobal[cv$index] = new double[2];
			}
			{
				int cv$threadCount = threadCount();
				cv$var277$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var277$stateProbabilityGlobal[cv$index] = new double[2];
			}
			int cv$threadCount = threadCount();
			cv$var290$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var290$stateProbabilityGlobal[cv$index] = new double[2];
		}
	}


	public NoisyOr$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample12() {
		state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void drawValueSample15() {
		state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void drawValueSample18() {
		state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void drawValueSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var223;
		if(state.flag1)
			var223 = state.p[0][i$var211];
		else
			var223 = 0.0;
		state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
		boolean reduceVar$var300$36 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$36 = (reduceVar$var300$36 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$36;
	}

	private final void drawValueSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var236;
		if(state.flag2)
			var236 = state.p[1][i$var211];
		else
			var236 = 0.0;
		state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
		boolean reduceVar$var300$37 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$37 = (reduceVar$var300$37 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$37;
	}

	private final void drawValueSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var249;
		if(state.flag3)
			var249 = state.p[2][i$var211];
		else
			var249 = 0.0;
		state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
		boolean reduceVar$var300$38 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$38 = (reduceVar$var300$38 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$38;
	}

	private final void drawValueSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var262;
		if(state.flag4)
			var262 = state.p[3][i$var211];
		else
			var262 = 0.0;
		state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
		boolean reduceVar$var300$39 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$39 = (reduceVar$var300$39 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$39;
	}

	private final void drawValueSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var275;
		if(state.flag5)
			var275 = state.p[4][i$var211];
		else
			var275 = 0.0;
		state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
		boolean reduceVar$var300$40 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$40 = (reduceVar$var300$40 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$40;
	}

	private final void drawValueSample3() {
		state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void drawValueSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var288;
		if(state.flag6)
			var288 = state.p[5][i$var211];
		else
			var288 = 0.0;
		state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
		boolean reduceVar$var300$41 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$41 = (reduceVar$var300$41 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$41;
	}

	private final void drawValueSample430(int i$var381, int j, int threadID$cv$j, Rng RNG$) {
		double var402;
		if(state.noisyOr[j])
			var402 = state.p13[j][i$var381];
		else
			var402 = 0.0;
		state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		boolean reduceVar$var414$7 = false;
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			reduceVar$var414$7 = (reduceVar$var414$7 || state.issues$var383[i$var381][cv$reduction435Index]);
		state.n13State[i$var381] = reduceVar$var414$7;
	}

	private final void drawValueSample6() {
		state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void drawValueSample9() {
		state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	private final void inferSample12() {
		state.constrainedFlag$sample12 = false;
		{
			state.flag4 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample278 || state.constrainedFlag$sample278[i$var211])) {
					state.constrainedFlag$sample12 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][3]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var12$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag4 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample278 || state.constrainedFlag$sample278[i$var211])) {
				double traceTempVariable$var262$2_1 = state.p[3][i$var211];
				state.constrainedFlag$sample12 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var12$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample12) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var12$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var12$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var12$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var12$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var12$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var12$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var12$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var12$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var12$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var12$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var12$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var12$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag4 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var12$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample15() {
		state.constrainedFlag$sample15 = false;
		{
			state.flag5 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample293 || state.constrainedFlag$sample293[i$var211])) {
					state.constrainedFlag$sample15 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][4]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var15$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag5 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample293 || state.constrainedFlag$sample293[i$var211])) {
				double traceTempVariable$var275$2_1 = state.p[4][i$var211];
				state.constrainedFlag$sample15 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var15$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample15) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var15$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var15$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var15$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var15$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var15$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var15$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var15$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var15$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var15$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var15$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var15$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag5 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var15$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample18() {
		state.constrainedFlag$sample18 = false;
		{
			state.flag6 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample308 || state.constrainedFlag$sample308[i$var211])) {
					state.constrainedFlag$sample18 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][5]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var18$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag6 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample308 || state.constrainedFlag$sample308[i$var211])) {
				double traceTempVariable$var288$2_1 = state.p[5][i$var211];
				state.constrainedFlag$sample18 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var18$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample18) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var18$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var18$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var18$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var18$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var18$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var18$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var18$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var18$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var18$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var18$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var18$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var18$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag6 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var18$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample233[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var225$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][0] = false;
			boolean reduceVar$var300$24 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$24 = (reduceVar$var300$24 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$24;
			double var223;
			if(state.flag1)
				var223 = state.p[0][i$var211];
			else
				var223 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((1.0 - var223)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample233[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample233[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][0] = true;
		boolean reduceVar$var300$24 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$24 = (reduceVar$var300$24 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$24;
		double var223;
		if(state.flag1)
			var223 = state.p[0][i$var211];
		else
			var223 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log(var223):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample233[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample233[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample233[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][0] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$25 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$25 = (reduceVar$var300$25 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$25;
		}
	}

	private final void inferSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample248[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var238$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][1] = false;
			boolean reduceVar$var300$26 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$26 = (reduceVar$var300$26 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$26;
			double var236;
			if(state.flag2)
				var236 = state.p[1][i$var211];
			else
				var236 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((1.0 - var236)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample248[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample248[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][1] = true;
		boolean reduceVar$var300$26 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$26 = (reduceVar$var300$26 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$26;
		double var236;
		if(state.flag2)
			var236 = state.p[1][i$var211];
		else
			var236 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log(var236):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample248[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample248[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample248[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][1] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$27 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$27 = (reduceVar$var300$27 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$27;
		}
	}

	private final void inferSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample263[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var251$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][2] = false;
			boolean reduceVar$var300$28 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$28 = (reduceVar$var300$28 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$28;
			double var249;
			if(state.flag3)
				var249 = state.p[2][i$var211];
			else
				var249 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((1.0 - var249)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample263[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample263[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][2] = true;
		boolean reduceVar$var300$28 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$28 = (reduceVar$var300$28 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$28;
		double var249;
		if(state.flag3)
			var249 = state.p[2][i$var211];
		else
			var249 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log(var249):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample263[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample263[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample263[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][2] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$29 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$29 = (reduceVar$var300$29 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$29;
		}
	}

	private final void inferSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample278[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var264$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][3] = false;
			boolean reduceVar$var300$30 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$30 = (reduceVar$var300$30 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$30;
			double var262;
			if(state.flag4)
				var262 = state.p[3][i$var211];
			else
				var262 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((1.0 - var262)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample278[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample278[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][3] = true;
		boolean reduceVar$var300$30 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$30 = (reduceVar$var300$30 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$30;
		double var262;
		if(state.flag4)
			var262 = state.p[3][i$var211];
		else
			var262 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log(var262):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample278[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample278[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample278[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][3] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$31 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$31 = (reduceVar$var300$31 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$31;
		}
	}

	private final void inferSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample293[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var277$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][4] = false;
			boolean reduceVar$var300$32 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$32 = (reduceVar$var300$32 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$32;
			double var275;
			if(state.flag5)
				var275 = state.p[4][i$var211];
			else
				var275 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((1.0 - var275)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample293[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample293[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][4] = true;
		boolean reduceVar$var300$32 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$32 = (reduceVar$var300$32 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$32;
		double var275;
		if(state.flag5)
			var275 = state.p[4][i$var211];
		else
			var275 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log(var275):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample293[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample293[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample293[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][4] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$33 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$33 = (reduceVar$var300$33 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$33;
		}
	}

	private final void inferSample3() {
		state.constrainedFlag$sample3 = false;
		{
			state.flag1 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample233 || state.constrainedFlag$sample233[i$var211])) {
					state.constrainedFlag$sample3 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][0]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var3$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag1 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample233 || state.constrainedFlag$sample233[i$var211])) {
				double traceTempVariable$var223$2_1 = state.p[0][i$var211];
				state.constrainedFlag$sample3 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var3$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample3) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var3$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var3$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var3$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var3$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var3$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var3$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var3$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var3$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var3$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var3$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var3$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var3$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag1 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var3$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample308[i$var211] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var290$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			state.issues$var213[i$var211][5] = false;
			boolean reduceVar$var300$34 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$34 = (reduceVar$var300$34 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$34;
			double var288;
			if(state.flag6)
				var288 = state.p[5][i$var211];
			else
				var288 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((1.0 - var288)):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample430) {
				if(state.noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					state.constrainedFlag$sample308[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					state.constrainedFlag$sample308[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		state.issues$var213[i$var211][5] = true;
		boolean reduceVar$var300$34 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$34 = (reduceVar$var300$34 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$34;
		double var288;
		if(state.flag6)
			var288 = state.p[5][i$var211];
		else
			var288 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log(var288):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample430) {
			if(state.noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				state.constrainedFlag$sample308[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				state.constrainedFlag$sample308[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample308[i$var211]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.issues$var213[i$var211][5] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$35 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$35 = (reduceVar$var300$35 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$35;
		}
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		{
			state.flag2 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample248 || state.constrainedFlag$sample248[i$var211])) {
					state.constrainedFlag$sample6 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][1]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var6$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag2 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample248 || state.constrainedFlag$sample248[i$var211])) {
				double traceTempVariable$var236$2_1 = state.p[1][i$var211];
				state.constrainedFlag$sample6 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var6$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample6) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var6$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var6$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var6$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var6$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var6$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var6$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var6$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var6$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var6$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var6$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var6$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag2 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var6$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		{
			state.flag3 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((state.fixedFlag$sample263 || state.constrainedFlag$sample263[i$var211])) {
					state.constrainedFlag$sample9 = true;
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][2]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var9$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.flag3 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((state.fixedFlag$sample263 || state.constrainedFlag$sample263[i$var211])) {
				double traceTempVariable$var249$2_1 = state.p[2][i$var211];
				state.constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		scratch.cv$var9$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample9) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var9$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var9$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var9$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var9$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var9$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var9$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var9$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var9$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var9$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var9$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var9$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.flag3 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var9$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!state.fixedProbFlag$sample12) {
			double cv$distributionAccumulator = Math.log((state.flag4?0.01:0.99));
			state.logProbability$flag4 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample12)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample12 = state.fixedFlag$sample12;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag4);
			if(state.fixedFlag$sample12)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag4);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!state.fixedProbFlag$sample15) {
			double cv$distributionAccumulator = Math.log((state.flag5?0.01:0.99));
			state.logProbability$flag5 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag5);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag5);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$distributionAccumulator = Math.log((state.flag6?0.01:0.99));
			state.logProbability$flag6 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag6);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag6);
		}
	}

	private final void logProbabilityValue$sample233() {
		if(!state.fixedProbFlag$sample233) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var223;
				if(state.flag1)
					var223 = state.p[0][i$var211];
				else
					var223 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((state.issues$var213[i$var211][0]?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample233[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample233)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample233 = (state.fixedFlag$sample233 && state.fixedFlag$sample3);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample233[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample233)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample248() {
		if(!state.fixedProbFlag$sample248) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var236;
				if(state.flag2)
					var236 = state.p[1][i$var211];
				else
					var236 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((state.issues$var213[i$var211][1]?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample248[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample248)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample248 = (state.fixedFlag$sample248 && state.fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample248[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample248)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample263() {
		if(!state.fixedProbFlag$sample263) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var249;
				if(state.flag3)
					var249 = state.p[2][i$var211];
				else
					var249 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((state.issues$var213[i$var211][2]?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample263[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample263)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample263 = (state.fixedFlag$sample263 && state.fixedFlag$sample9);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample263[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample263)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample278() {
		if(!state.fixedProbFlag$sample278) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var262;
				if(state.flag4)
					var262 = state.p[3][i$var211];
				else
					var262 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((state.issues$var213[i$var211][3]?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample278[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample278)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample278 = (state.fixedFlag$sample278 && state.fixedFlag$sample12);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample278[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample278)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample293() {
		if(!state.fixedProbFlag$sample293) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var275;
				if(state.flag5)
					var275 = state.p[4][i$var211];
				else
					var275 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((state.issues$var213[i$var211][4]?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample293[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample293)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample293 = (state.fixedFlag$sample293 && state.fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample293[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample293)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample3() {
		if(!state.fixedProbFlag$sample3) {
			double cv$distributionAccumulator = Math.log((state.flag1?0.01:0.99));
			state.logProbability$flag1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample3)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample3 = state.fixedFlag$sample3;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag1);
			if(state.fixedFlag$sample3)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag1);
		}
	}

	private final void logProbabilityValue$sample308() {
		if(!state.fixedProbFlag$sample308) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var288;
				if(state.flag6)
					var288 = state.p[5][i$var211];
				else
					var288 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((state.issues$var213[i$var211][5]?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample308[i$var211] = cv$distributionAccumulator;
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample308)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample308 = (state.fixedFlag$sample308 && state.fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample308[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample308)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample430() {
		if(!state.fixedProbFlag$sample430) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(state.noisyOr[j])
					var402 = state.p13[j][0];
				else
					var402 = 0.0;
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((state.issues$var383[0][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				state.logProbability$sample430[0][j] = cv$weightedProbability;
				state.logProbability$n13State = (state.logProbability$n13State + cv$weightedProbability);
			}
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(state.noisyOr[j])
					var402 = state.p13[j][1];
				else
					var402 = 0.0;
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((state.issues$var383[1][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				state.logProbability$sample430[1][j] = cv$weightedProbability;
				state.logProbability$n13State = (state.logProbability$n13State + cv$weightedProbability);
			}
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample430)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample430 = ((((((state.fixedFlag$sample430 && state.fixedFlag$sample233) && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double cv$sampleValue = state.logProbability$sample430[0][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$n13State = (state.logProbability$n13State + cv$sampleValue);
			}
			for(int j = 0; j < 5; j += 1) {
				double cv$sampleValue = state.logProbability$sample430[1][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$n13State = (state.logProbability$n13State + cv$sampleValue);
			}
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample430)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!state.fixedProbFlag$sample6) {
			double cv$distributionAccumulator = Math.log((state.flag2?0.01:0.99));
			state.logProbability$flag2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag2);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag2);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = Math.log((state.flag3?0.01:0.99));
			state.logProbability$flag3 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag3);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag3);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.fixedFlag$sample233) {
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!state.fixedFlag$sample248) {
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!state.fixedFlag$sample263) {
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!state.fixedFlag$sample278) {
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!state.fixedFlag$sample293) {
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!state.fixedFlag$sample308) {
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						if((((((!state.fixedFlag$sample233 || !state.fixedFlag$sample248) || !state.fixedFlag$sample263) || !state.fixedFlag$sample278) || !state.fixedFlag$sample293) || !state.fixedFlag$sample308)) {
							boolean reduceVar$var300$42 = false;
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								reduceVar$var300$42 = (reduceVar$var300$42 || state.issues$var213[i$var211][cv$reduction313Index]);
							state.noisyOr[i$var211] = reduceVar$var300$42;
						}
					}
			}
		);
		if(!state.fixedFlag$sample430)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							boolean reduceVar$var414$8 = false;
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								reduceVar$var414$8 = (reduceVar$var414$8 || state.issues$var383[i$var381][cv$reduction435Index]);
							state.n13State[i$var381] = reduceVar$var414$8;
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.fixedFlag$sample233) {
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!state.fixedFlag$sample248) {
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!state.fixedFlag$sample263) {
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!state.fixedFlag$sample278) {
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!state.fixedFlag$sample293) {
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!state.fixedFlag$sample308) {
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$46 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$46 = (reduceVar$var300$46 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$46;
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!state.fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$12 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$12 = (reduceVar$var414$12 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$12;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.fixedFlag$sample233) {
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!state.fixedFlag$sample248) {
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!state.fixedFlag$sample263) {
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!state.fixedFlag$sample278) {
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!state.fixedFlag$sample293) {
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!state.fixedFlag$sample308) {
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$43 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$43 = (reduceVar$var300$43 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$43;
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!state.fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$9 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$9 = (reduceVar$var414$9 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$9;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.fixedFlag$sample233) {
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!state.fixedFlag$sample248) {
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!state.fixedFlag$sample263) {
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!state.fixedFlag$sample278) {
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!state.fixedFlag$sample293) {
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!state.fixedFlag$sample308) {
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						if((((((!state.fixedFlag$sample233 || !state.fixedFlag$sample248) || !state.fixedFlag$sample263) || !state.fixedFlag$sample278) || !state.fixedFlag$sample293) || !state.fixedFlag$sample308)) {
							boolean reduceVar$var300$44 = false;
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								reduceVar$var300$44 = (reduceVar$var300$44 || state.issues$var213[i$var211][cv$reduction313Index]);
							state.noisyOr[i$var211] = reduceVar$var300$44;
						}
					}
			}
		);
		if(!state.fixedFlag$sample430)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							boolean reduceVar$var414$10 = false;
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								reduceVar$var414$10 = (reduceVar$var414$10 || state.issues$var383[i$var381][cv$reduction435Index]);
							state.n13State[i$var381] = reduceVar$var414$10;
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.fixedFlag$sample233) {
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!state.fixedFlag$sample248) {
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!state.fixedFlag$sample263) {
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!state.fixedFlag$sample278) {
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!state.fixedFlag$sample293) {
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!state.fixedFlag$sample308) {
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$45 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$45 = (reduceVar$var300$45 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$45;
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!state.fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$11 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$11 = (reduceVar$var414$11 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$11;
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample3)
				inferSample3();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample18)
				inferSample18();
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!state.fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
		} else {
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!state.fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample18)
				inferSample18();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample3)
				inferSample3();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample3)
			drawValueSample3();
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		if(!state.constrainedFlag$sample12)
			drawValueSample12();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
		if(!state.constrainedFlag$sample18)
			drawValueSample18();
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.constrainedFlag$sample233[i$var211])
							drawValueSample233(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample248[i$var211])
							drawValueSample248(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample263[i$var211])
							drawValueSample263(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample278[i$var211])
							drawValueSample278(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample293[i$var211])
							drawValueSample293(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample308[i$var211])
							drawValueSample308(i$var211, threadID$i$var211, RNG$1);
					}
			}
		);
		if(!state.fixedFlag$sample430)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											drawValueSample430(i$var381, j, threadID$j, RNG$2);
								}
							);
						}
				}
			);

	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample3)
			state.logProbability$flag1 = Double.NaN;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$flag2 = Double.NaN;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$flag3 = Double.NaN;
		if(!state.fixedProbFlag$sample12)
			state.logProbability$flag4 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$flag5 = Double.NaN;
		if(!state.fixedProbFlag$sample18)
			state.logProbability$flag6 = Double.NaN;
		state.logProbability$issues$var213 = 0.0;
		state.logProbability$noisyOr = 0.0;
		if(!state.fixedProbFlag$sample233) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample233[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample248[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample263[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample278[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample293[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample308[i$var211] = Double.NaN;
		}
		state.logProbability$issues$var383 = 0.0;
		state.logProbability$n13State = 0.0;
		if(!state.fixedProbFlag$sample430) {
			for(int j = 0; j < 5; j += 1)
				state.logProbability$sample430[0][j] = Double.NaN;
			for(int j = 0; j < 5; j += 1)
				state.logProbability$sample430[1][j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		double[] var23 = state.p[0];
		var23[0] = 0.0;
		var23[1] = 1.0;
		var23[2] = 0.0;
		var23[3] = 0.0;
		var23[4] = 0.0;
		double[] var53 = state.p[1];
		var53[0] = 0.5;
		var53[1] = 0.5;
		var53[2] = 0.0;
		var53[3] = 0.0;
		var53[4] = 0.0;
		double[] var81 = state.p[2];
		var81[0] = 0.0;
		var81[1] = 0.0;
		var81[2] = 0.0;
		var81[3] = 1.0;
		var81[4] = 0.0;
		double[] var111 = state.p[3];
		var111[0] = 0.0;
		var111[1] = 0.0;
		var111[2] = 0.0;
		var111[3] = 1.0;
		var111[4] = 0.0;
		double[] var141 = state.p[4];
		var141[0] = 0.0;
		var141[1] = 0.0;
		var141[2] = 1.0;
		var141[3] = 0.0;
		var141[4] = 0.0;
		double[] var171 = state.p[5];
		var171[0] = 0.0;
		var171[1] = 0.0;
		var171[2] = 1.0;
		var171[3] = 0.0;
		var171[4] = 0.0;
		double[] var306 = state.p13[0];
		var306[0] = 0.1;
		var306[1] = 0.9;
		double[] var319 = state.p13[1];
		var319[0] = 1.0;
		var319[1] = 0.0;
		double[] var332 = state.p13[2];
		var332[0] = 0.5;
		var332[1] = 0.5;
		double[] var345 = state.p13[3];
		var345[0] = 0.5;
		var345[1] = 0.5;
		double[] var358 = state.p13[4];
		var358[0] = 0.0;
		var358[1] = 1.0;
		for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < state.constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
			state.constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
		for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < state.constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
			state.constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
		for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < state.constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
			state.constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
		for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < state.constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
			state.constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
		for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < state.constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
			state.constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
		for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < state.constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
			state.constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample3)
			logProbabilityValue$sample3();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(state.fixedFlag$sample233)
			logProbabilityValue$sample233();
		if(state.fixedFlag$sample248)
			logProbabilityValue$sample248();
		if(state.fixedFlag$sample263)
			logProbabilityValue$sample263();
		if(state.fixedFlag$sample278)
			logProbabilityValue$sample278();
		if(state.fixedFlag$sample293)
			logProbabilityValue$sample293();
		if(state.fixedFlag$sample308)
			logProbabilityValue$sample308();
		if(state.fixedFlag$sample430)
			logProbabilityValue$sample430();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	@Override
	public final void propagateObservedValues() {}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						boolean reduceVar$var300$47 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$47 = (reduceVar$var300$47 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$47;
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$i$var381, int forEnd$i$var381, int threadID$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var381 = forStart$i$var381; i$var381 < forEnd$i$var381; i$var381 += 1) {
						boolean reduceVar$var414$13 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$13 = (reduceVar$var414$13 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$13;
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model NoisyOr() {\n"
		     + "\n"
		     + "\n"
		     + "    // 1)\n"
		     + "    double prior1 = 0.01;\n"
		     + "    boolean flag1 = bernoulli(prior1).sample();\n"
		     + "    \n"
		     + "    // 2)\n"
		     + "    double prior2 = 0.01;\n"
		     + "    boolean flag2 = bernoulli(prior2).sample();\n"
		     + "    \n"
		     + "    // 3)\n"
		     + "    double prior3 = 0.01;\n"
		     + "    boolean flag3 = bernoulli(prior3).sample();\n"
		     + "    \n"
		     + "    // 4)\n"
		     + "    double prior4 = 0.01;\n"
		     + "    boolean flag4 = bernoulli(prior4).sample();\n"
		     + "    \n"
		     + "    // 5)\n"
		     + "    double prior5 = 0.01;\n"
		     + "    boolean flag5 = bernoulli(prior5).sample();\n"
		     + "    \n"
		     + "    // 6)\n"
		     + "    double prior6 = 0.01;\n"
		     + "    boolean flag6 = bernoulli(prior6).sample();\n"
		     + "    \n"
		     + "    // Start n12\n"
		     + "    double[][] p = new double[6][];\n"
		     + "    p[0] = new double[] {0,1,0,0,0};\n"
		     + "    p[1] = new double[] {0.5,0.5,0,0,0};\n"
		     + "    p[2] = new double[] {0,0,0,1,0};\n"
		     + "    p[3] = new double[] {0,0,0,1,0};\n"
		     + "    p[4] = new double[] {0,0,1,0,0};\n"
		     + "    p[5] = new double[] {0,0,1,0,0};\n"
		     + "    \n"
		     + "    boolean[] noisyOr = new boolean[5];\n"
		     + "    \n"
		     + "    for(int i=0; i<5; i++) {\n"
		     + "        boolean[] issues = new boolean[6];\n"
		     + "        issues[0] = bernoulli(flag1?p[0][i]:0).sample();\n"
		     + "        issues[1] = bernoulli(flag2?p[1][i]:0).sample();\n"
		     + "        issues[2] = bernoulli(flag3?p[2][i]:0).sample();\n"
		     + "        issues[3] = bernoulli(flag4?p[3][i]:0).sample();\n"
		     + "        issues[4] = bernoulli(flag5?p[4][i]:0).sample();\n"
		     + "        issues[5] = bernoulli(flag6?p[5][i]:0).sample();\n"
		     + "        \n"
		     + "        noisyOr[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Starting n13\n"
		     + "    double[][] p13 = new double[5][];\n"
		     + "    p13[0] = new double[] {0.1, 0.9};\n"
		     + "    p13[1] = new double[] {1.0, 0.0};\n"
		     + "    p13[2] = new double[] {0.5, 0.5};\n"
		     + "    p13[3] = new double[] {0.5, 0.5};\n"
		     + "    p13[4] = new double[] {0.0, 1.0};\n"
		     + "    \n"
		     + "    boolean[] n13State = new boolean[2];\n"
		     + "    \n"
		     + "    for(int i=0; i<2; i++) {\n"
		     + "        boolean[] issues = new boolean[5];\n"
		     + "        for(int j=0; j<5; j++)\n"
		     + "            issues[j] = bernoulli(noisyOr[j]?p13[j][i]:0).sample();\n"
		     + "        \n"
		     + "        n13State[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}