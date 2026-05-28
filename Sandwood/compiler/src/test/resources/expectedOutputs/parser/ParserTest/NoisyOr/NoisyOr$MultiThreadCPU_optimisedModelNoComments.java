package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements NoisyOr$CoreInterface {
	private boolean constrainedFlag$sample12 = true;
	private boolean constrainedFlag$sample15 = true;
	private boolean constrainedFlag$sample18 = true;
	private boolean[] constrainedFlag$sample233;
	private boolean[] constrainedFlag$sample248;
	private boolean[] constrainedFlag$sample263;
	private boolean[] constrainedFlag$sample278;
	private boolean[] constrainedFlag$sample293;
	private boolean constrainedFlag$sample3 = true;
	private boolean[] constrainedFlag$sample308;
	private boolean constrainedFlag$sample6 = true;
	private boolean constrainedFlag$sample9 = true;
	private double[] cv$var12$stateProbabilityGlobal;
	private double[] cv$var15$stateProbabilityGlobal;
	private double[] cv$var18$stateProbabilityGlobal;
	private double[][] cv$var225$stateProbabilityGlobal;
	private double[][] cv$var238$stateProbabilityGlobal;
	private double[][] cv$var251$stateProbabilityGlobal;
	private double[][] cv$var264$stateProbabilityGlobal;
	private double[][] cv$var277$stateProbabilityGlobal;
	private double[][] cv$var290$stateProbabilityGlobal;
	private double[] cv$var3$stateProbabilityGlobal;
	private double[] cv$var6$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample233 = false;
	private boolean fixedFlag$sample248 = false;
	private boolean fixedFlag$sample263 = false;
	private boolean fixedFlag$sample278 = false;
	private boolean fixedFlag$sample293 = false;
	private boolean fixedFlag$sample3 = false;
	private boolean fixedFlag$sample308 = false;
	private boolean fixedFlag$sample430 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample233 = false;
	private boolean fixedProbFlag$sample248 = false;
	private boolean fixedProbFlag$sample263 = false;
	private boolean fixedProbFlag$sample278 = false;
	private boolean fixedProbFlag$sample293 = false;
	private boolean fixedProbFlag$sample3 = false;
	private boolean fixedProbFlag$sample308 = false;
	private boolean fixedProbFlag$sample430 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;
	private boolean flag5;
	private boolean flag6;
	private boolean[][] issues$var213;
	private boolean[][] issues$var383;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$flag1;
	private double logProbability$flag2;
	private double logProbability$flag3;
	private double logProbability$flag4;
	private double logProbability$flag5;
	private double logProbability$flag6;
	private double logProbability$issues$var213;
	private double logProbability$issues$var383;
	private double logProbability$n13State;
	private double logProbability$noisyOr;
	private double[] logProbability$sample233;
	private double[] logProbability$sample248;
	private double[] logProbability$sample263;
	private double[] logProbability$sample278;
	private double[] logProbability$sample293;
	private double[] logProbability$sample308;
	private double[][] logProbability$sample430;
	private boolean[] n13State;
	private boolean[] noisyOr;
	private double[][] p;
	private double[][] p13;
	private boolean system$gibbsForward = true;

	public NoisyOr$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value, boolean allocated$) {
		fixedFlag$sample12 = cv$value;
		constrainedFlag$sample12 = (cv$value || constrainedFlag$sample12);
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		fixedProbFlag$sample278 = (cv$value && fixedProbFlag$sample278);
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value, boolean allocated$) {
		fixedFlag$sample15 = cv$value;
		constrainedFlag$sample15 = (cv$value || constrainedFlag$sample15);
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		fixedProbFlag$sample293 = (cv$value && fixedProbFlag$sample293);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value, boolean allocated$) {
		fixedFlag$sample18 = cv$value;
		constrainedFlag$sample18 = (cv$value || constrainedFlag$sample18);
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample308 = (cv$value && fixedProbFlag$sample308);
	}

	@Override
	public final boolean get$fixedFlag$sample233() {
		return fixedFlag$sample233;
	}

	@Override
	public final void set$fixedFlag$sample233(boolean cv$value, boolean allocated$) {
		fixedFlag$sample233 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
				constrainedFlag$sample233[index$constrainedFlag$sample233$1] = cv$value;
		}
		fixedProbFlag$sample233 = (cv$value && fixedProbFlag$sample233);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample248() {
		return fixedFlag$sample248;
	}

	@Override
	public final void set$fixedFlag$sample248(boolean cv$value, boolean allocated$) {
		fixedFlag$sample248 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
				constrainedFlag$sample248[index$constrainedFlag$sample248$1] = cv$value;
		}
		fixedProbFlag$sample248 = (cv$value && fixedProbFlag$sample248);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample263() {
		return fixedFlag$sample263;
	}

	@Override
	public final void set$fixedFlag$sample263(boolean cv$value, boolean allocated$) {
		fixedFlag$sample263 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
				constrainedFlag$sample263[index$constrainedFlag$sample263$1] = cv$value;
		}
		fixedProbFlag$sample263 = (cv$value && fixedProbFlag$sample263);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample278() {
		return fixedFlag$sample278;
	}

	@Override
	public final void set$fixedFlag$sample278(boolean cv$value, boolean allocated$) {
		fixedFlag$sample278 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
				constrainedFlag$sample278[index$constrainedFlag$sample278$1] = cv$value;
		}
		fixedProbFlag$sample278 = (cv$value && fixedProbFlag$sample278);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample293() {
		return fixedFlag$sample293;
	}

	@Override
	public final void set$fixedFlag$sample293(boolean cv$value, boolean allocated$) {
		fixedFlag$sample293 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
				constrainedFlag$sample293[index$constrainedFlag$sample293$1] = cv$value;
		}
		fixedProbFlag$sample293 = (cv$value && fixedProbFlag$sample293);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample3() {
		return fixedFlag$sample3;
	}

	@Override
	public final void set$fixedFlag$sample3(boolean cv$value, boolean allocated$) {
		fixedFlag$sample3 = cv$value;
		constrainedFlag$sample3 = (cv$value || constrainedFlag$sample3);
		fixedProbFlag$sample3 = (cv$value && fixedProbFlag$sample3);
		fixedProbFlag$sample233 = (cv$value && fixedProbFlag$sample233);
	}

	@Override
	public final boolean get$fixedFlag$sample308() {
		return fixedFlag$sample308;
	}

	@Override
	public final void set$fixedFlag$sample308(boolean cv$value, boolean allocated$) {
		fixedFlag$sample308 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
				constrainedFlag$sample308[index$constrainedFlag$sample308$1] = cv$value;
		}
		fixedProbFlag$sample308 = (cv$value && fixedProbFlag$sample308);
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample430() {
		return fixedFlag$sample430;
	}

	@Override
	public final void set$fixedFlag$sample430(boolean cv$value, boolean allocated$) {
		fixedFlag$sample430 = cv$value;
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
		fixedFlag$sample6 = cv$value;
		constrainedFlag$sample6 = (cv$value || constrainedFlag$sample6);
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample248 = (cv$value && fixedProbFlag$sample248);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
		fixedFlag$sample9 = cv$value;
		constrainedFlag$sample9 = (cv$value || constrainedFlag$sample9);
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample263 = (cv$value && fixedProbFlag$sample263);
	}

	@Override
	public final boolean get$flag1() {
		return flag1;
	}

	@Override
	public final void set$flag1(boolean cv$value, boolean allocated$) {
		flag1 = cv$value;
		fixedProbFlag$sample3 = false;
		fixedProbFlag$sample233 = false;
	}

	@Override
	public final boolean get$flag2() {
		return flag2;
	}

	@Override
	public final void set$flag2(boolean cv$value, boolean allocated$) {
		flag2 = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample248 = false;
	}

	@Override
	public final boolean get$flag3() {
		return flag3;
	}

	@Override
	public final void set$flag3(boolean cv$value, boolean allocated$) {
		flag3 = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample263 = false;
	}

	@Override
	public final boolean get$flag4() {
		return flag4;
	}

	@Override
	public final void set$flag4(boolean cv$value, boolean allocated$) {
		flag4 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample278 = false;
	}

	@Override
	public final boolean get$flag5() {
		return flag5;
	}

	@Override
	public final void set$flag5(boolean cv$value, boolean allocated$) {
		flag5 = cv$value;
		fixedProbFlag$sample15 = false;
		fixedProbFlag$sample293 = false;
	}

	@Override
	public final boolean get$flag6() {
		return flag6;
	}

	@Override
	public final void set$flag6(boolean cv$value, boolean allocated$) {
		flag6 = cv$value;
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample308 = false;
	}

	@Override
	public final boolean[][] get$issues$var213() {
		return issues$var213;
	}

	@Override
	public final void set$issues$var213(boolean[][] cv$value, boolean allocated$) {
		issues$var213 = cv$value;
	}

	@Override
	public final boolean[][] get$issues$var383() {
		return issues$var383;
	}

	@Override
	public final void set$issues$var383(boolean[][] cv$value, boolean allocated$) {
		issues$var383 = cv$value;
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
	public final double get$logProbability$flag1() {
		return logProbability$flag1;
	}

	@Override
	public final double get$logProbability$flag2() {
		return logProbability$flag2;
	}

	@Override
	public final double get$logProbability$flag3() {
		return logProbability$flag3;
	}

	@Override
	public final double get$logProbability$flag4() {
		return logProbability$flag4;
	}

	@Override
	public final double get$logProbability$flag5() {
		return logProbability$flag5;
	}

	@Override
	public final double get$logProbability$flag6() {
		return logProbability$flag6;
	}

	@Override
	public final double get$logProbability$n13State() {
		return logProbability$n13State;
	}

	@Override
	public final double get$logProbability$noisyOr() {
		return logProbability$noisyOr;
	}

	@Override
	public final boolean[] get$n13State() {
		return n13State;
	}

	@Override
	public final boolean[] get$noisyOr() {
		return noisyOr;
	}

	@Override
	public final double[][] get$p() {
		return p;
	}

	@Override
	public final double[][] get$p13() {
		return p13;
	}

	@Override
	public final double get$prior1() {
		return 0.01;
	}

	@Override
	public final double get$prior2() {
		return 0.01;
	}

	@Override
	public final double get$prior3() {
		return 0.01;
	}

	@Override
	public final double get$prior4() {
		return 0.01;
	}

	@Override
	public final double get$prior5() {
		return 0.01;
	}

	@Override
	public final double get$prior6() {
		return 0.01;
	}

	private final void drawValueSample12() {
		flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void drawValueSample15() {
		flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void drawValueSample18() {
		flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void drawValueSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var223;
		if(flag1)
			var223 = p[0][i$var211];
		else
			var223 = 0.0;
		issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
		boolean reduceVar$var300$36 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$36 = (reduceVar$var300$36 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$36;
	}

	private final void drawValueSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var236;
		if(flag2)
			var236 = p[1][i$var211];
		else
			var236 = 0.0;
		issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
		boolean reduceVar$var300$37 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$37 = (reduceVar$var300$37 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$37;
	}

	private final void drawValueSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var249;
		if(flag3)
			var249 = p[2][i$var211];
		else
			var249 = 0.0;
		issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
		boolean reduceVar$var300$38 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$38 = (reduceVar$var300$38 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$38;
	}

	private final void drawValueSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var262;
		if(flag4)
			var262 = p[3][i$var211];
		else
			var262 = 0.0;
		issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
		boolean reduceVar$var300$39 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$39 = (reduceVar$var300$39 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$39;
	}

	private final void drawValueSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var275;
		if(flag5)
			var275 = p[4][i$var211];
		else
			var275 = 0.0;
		issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
		boolean reduceVar$var300$40 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$40 = (reduceVar$var300$40 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$40;
	}

	private final void drawValueSample3() {
		flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void drawValueSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var288;
		if(flag6)
			var288 = p[5][i$var211];
		else
			var288 = 0.0;
		issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
		boolean reduceVar$var300$41 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$41 = (reduceVar$var300$41 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$41;
	}

	private final void drawValueSample430(int i$var381, int j, int threadID$cv$j, Rng RNG$) {
		double var402;
		if(noisyOr[j])
			var402 = p13[j][i$var381];
		else
			var402 = 0.0;
		issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		boolean reduceVar$var414$7 = false;
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			reduceVar$var414$7 = (reduceVar$var414$7 || issues$var383[i$var381][cv$reduction435Index]);
		n13State[i$var381] = reduceVar$var414$7;
	}

	private final void drawValueSample6() {
		flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void drawValueSample9() {
		flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
	}

	private final void inferSample12() {
		constrainedFlag$sample12 = false;
		{
			flag4 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample278 || constrainedFlag$sample278[i$var211])) {
					constrainedFlag$sample12 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][3]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var12$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag4 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample278 || constrainedFlag$sample278[i$var211])) {
				double traceTempVariable$var262$2_1 = p[3][i$var211];
				constrainedFlag$sample12 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[i$var211][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var12$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample12) {
			double cv$logSum;
			double cv$lseMax = cv$var12$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var12$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var12$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var12$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var12$stateProbabilityGlobal[0] = 0.5;
				cv$var12$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var12$stateProbabilityGlobal[0] = Math.exp((cv$var12$stateProbabilityGlobal[0] - cv$logSum));
				cv$var12$stateProbabilityGlobal[1] = Math.exp((cv$var12$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var12$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var12$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag4 = (DistributionSampling.sampleCategorical(RNG$, cv$var12$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample15() {
		constrainedFlag$sample15 = false;
		{
			flag5 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample293 || constrainedFlag$sample293[i$var211])) {
					constrainedFlag$sample15 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][4]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var15$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag5 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample293 || constrainedFlag$sample293[i$var211])) {
				double traceTempVariable$var275$2_1 = p[4][i$var211];
				constrainedFlag$sample15 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[i$var211][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var15$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample15) {
			double cv$logSum;
			double cv$lseMax = cv$var15$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var15$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var15$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var15$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var15$stateProbabilityGlobal[0] = 0.5;
				cv$var15$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var15$stateProbabilityGlobal[0] = Math.exp((cv$var15$stateProbabilityGlobal[0] - cv$logSum));
				cv$var15$stateProbabilityGlobal[1] = Math.exp((cv$var15$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var15$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag5 = (DistributionSampling.sampleCategorical(RNG$, cv$var15$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample18() {
		constrainedFlag$sample18 = false;
		{
			flag6 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample308 || constrainedFlag$sample308[i$var211])) {
					constrainedFlag$sample18 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][5]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var18$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag6 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample308 || constrainedFlag$sample308[i$var211])) {
				double traceTempVariable$var288$2_1 = p[5][i$var211];
				constrainedFlag$sample18 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[i$var211][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var18$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample18) {
			double cv$logSum;
			double cv$lseMax = cv$var18$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var18$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var18$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var18$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var18$stateProbabilityGlobal[0] = 0.5;
				cv$var18$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var18$stateProbabilityGlobal[0] = Math.exp((cv$var18$stateProbabilityGlobal[0] - cv$logSum));
				cv$var18$stateProbabilityGlobal[1] = Math.exp((cv$var18$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var18$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var18$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag6 = (DistributionSampling.sampleCategorical(RNG$, cv$var18$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample233[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var225$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][0] = false;
			boolean reduceVar$var300$24 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$24 = (reduceVar$var300$24 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$24;
			double var223;
			if(flag1)
				var223 = p[0][i$var211];
			else
				var223 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((1.0 - var223)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample233[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample233[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][0] = true;
		boolean reduceVar$var300$24 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$24 = (reduceVar$var300$24 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$24;
		double var223;
		if(flag1)
			var223 = p[0][i$var211];
		else
			var223 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log(var223):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample233[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample233[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample233[i$var211]) {
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
			issues$var213[i$var211][0] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$25 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$25 = (reduceVar$var300$25 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$25;
		}
	}

	private final void inferSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample248[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var238$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][1] = false;
			boolean reduceVar$var300$26 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$26 = (reduceVar$var300$26 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$26;
			double var236;
			if(flag2)
				var236 = p[1][i$var211];
			else
				var236 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((1.0 - var236)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample248[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample248[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][1] = true;
		boolean reduceVar$var300$26 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$26 = (reduceVar$var300$26 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$26;
		double var236;
		if(flag2)
			var236 = p[1][i$var211];
		else
			var236 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log(var236):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample248[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample248[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample248[i$var211]) {
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
			issues$var213[i$var211][1] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$27 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$27 = (reduceVar$var300$27 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$27;
		}
	}

	private final void inferSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample263[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var251$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][2] = false;
			boolean reduceVar$var300$28 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$28 = (reduceVar$var300$28 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$28;
			double var249;
			if(flag3)
				var249 = p[2][i$var211];
			else
				var249 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((1.0 - var249)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample263[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample263[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][2] = true;
		boolean reduceVar$var300$28 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$28 = (reduceVar$var300$28 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$28;
		double var249;
		if(flag3)
			var249 = p[2][i$var211];
		else
			var249 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log(var249):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample263[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample263[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample263[i$var211]) {
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
			issues$var213[i$var211][2] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$29 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$29 = (reduceVar$var300$29 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$29;
		}
	}

	private final void inferSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample278[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var264$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][3] = false;
			boolean reduceVar$var300$30 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$30 = (reduceVar$var300$30 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$30;
			double var262;
			if(flag4)
				var262 = p[3][i$var211];
			else
				var262 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((1.0 - var262)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample278[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample278[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][3] = true;
		boolean reduceVar$var300$30 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$30 = (reduceVar$var300$30 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$30;
		double var262;
		if(flag4)
			var262 = p[3][i$var211];
		else
			var262 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log(var262):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample278[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample278[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample278[i$var211]) {
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
			issues$var213[i$var211][3] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$31 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$31 = (reduceVar$var300$31 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$31;
		}
	}

	private final void inferSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample293[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var277$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][4] = false;
			boolean reduceVar$var300$32 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$32 = (reduceVar$var300$32 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$32;
			double var275;
			if(flag5)
				var275 = p[4][i$var211];
			else
				var275 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((1.0 - var275)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample293[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample293[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][4] = true;
		boolean reduceVar$var300$32 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$32 = (reduceVar$var300$32 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$32;
		double var275;
		if(flag5)
			var275 = p[4][i$var211];
		else
			var275 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log(var275):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample293[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample293[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample293[i$var211]) {
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
			issues$var213[i$var211][4] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$33 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$33 = (reduceVar$var300$33 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$33;
		}
	}

	private final void inferSample3() {
		constrainedFlag$sample3 = false;
		{
			flag1 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample233 || constrainedFlag$sample233[i$var211])) {
					constrainedFlag$sample3 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][0]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var3$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag1 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample233 || constrainedFlag$sample233[i$var211])) {
				double traceTempVariable$var223$2_1 = p[0][i$var211];
				constrainedFlag$sample3 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[i$var211][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var3$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample3) {
			double cv$logSum;
			double cv$lseMax = cv$var3$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var3$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var3$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var3$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var3$stateProbabilityGlobal[0] = 0.5;
				cv$var3$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var3$stateProbabilityGlobal[0] = Math.exp((cv$var3$stateProbabilityGlobal[0] - cv$logSum));
				cv$var3$stateProbabilityGlobal[1] = Math.exp((cv$var3$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var3$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var3$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag1 = (DistributionSampling.sampleCategorical(RNG$, cv$var3$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		constrainedFlag$sample308[i$var211] = false;
		double[] cv$stateProbabilityLocal = cv$var290$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			issues$var213[i$var211][5] = false;
			boolean reduceVar$var300$34 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$34 = (reduceVar$var300$34 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$34;
			double var288;
			if(flag6)
				var288 = p[5][i$var211];
			else
				var288 = 0.0;
			double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((1.0 - var288)):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample430) {
				if(noisyOr[i$var211]) {
					{
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					constrainedFlag$sample308[i$var211] = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				} else {
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					constrainedFlag$sample308[i$var211] = true;
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		issues$var213[i$var211][5] = true;
		boolean reduceVar$var300$34 = false;
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			reduceVar$var300$34 = (reduceVar$var300$34 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$34;
		double var288;
		if(flag6)
			var288 = p[5][i$var211];
		else
			var288 = 0.0;
		double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log(var288):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample430) {
			if(noisyOr[i$var211]) {
				{
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				constrainedFlag$sample308[i$var211] = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				constrainedFlag$sample308[i$var211] = true;
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample308[i$var211]) {
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
			issues$var213[i$var211][5] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			boolean reduceVar$var300$35 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				reduceVar$var300$35 = (reduceVar$var300$35 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$35;
		}
	}

	private final void inferSample6() {
		constrainedFlag$sample6 = false;
		{
			flag2 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample248 || constrainedFlag$sample248[i$var211])) {
					constrainedFlag$sample6 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][1]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var6$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag2 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample248 || constrainedFlag$sample248[i$var211])) {
				double traceTempVariable$var236$2_1 = p[1][i$var211];
				constrainedFlag$sample6 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[i$var211][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var6$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample6) {
			double cv$logSum;
			double cv$lseMax = cv$var6$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var6$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var6$stateProbabilityGlobal[0] = 0.5;
				cv$var6$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var6$stateProbabilityGlobal[0] = Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$logSum));
				cv$var6$stateProbabilityGlobal[1] = Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var6$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag2 = (DistributionSampling.sampleCategorical(RNG$, cv$var6$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample9() {
		constrainedFlag$sample9 = false;
		{
			flag3 = false;
			double cv$accumulatedProbabilities = -0.01005033585350145;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if((fixedFlag$sample263 || constrainedFlag$sample263[i$var211])) {
					constrainedFlag$sample9 = true;
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][2]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			cv$var9$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		flag3 = true;
		double cv$accumulatedProbabilities = -4.605170185988091;
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if((fixedFlag$sample263 || constrainedFlag$sample263[i$var211])) {
				double traceTempVariable$var249$2_1 = p[2][i$var211];
				constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[i$var211][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		cv$var9$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample9) {
			double cv$logSum;
			double cv$lseMax = cv$var9$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var9$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var9$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var9$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var9$stateProbabilityGlobal[0] = 0.5;
				cv$var9$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var9$stateProbabilityGlobal[0] = Math.exp((cv$var9$stateProbabilityGlobal[0] - cv$logSum));
				cv$var9$stateProbabilityGlobal[1] = Math.exp((cv$var9$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var9$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			flag3 = (DistributionSampling.sampleCategorical(RNG$, cv$var9$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$distributionAccumulator = Math.log((flag4?0.01:0.99));
			logProbability$flag4 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag4);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag4);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$distributionAccumulator = Math.log((flag5?0.01:0.99));
			logProbability$flag5 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag5);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag5);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$distributionAccumulator = Math.log((flag6?0.01:0.99));
			logProbability$flag6 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag6);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag6);
		}
	}

	private final void logProbabilityValue$sample233() {
		if(!fixedProbFlag$sample233) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((issues$var213[i$var211][0]?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample233[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample233)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample233 = (fixedFlag$sample233 && fixedFlag$sample3);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample233[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample233)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample248() {
		if(!fixedProbFlag$sample248) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((issues$var213[i$var211][1]?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample248[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample248)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample248 = (fixedFlag$sample248 && fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample248[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample248)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample263() {
		if(!fixedProbFlag$sample263) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((issues$var213[i$var211][2]?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample263[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample263)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample263 = (fixedFlag$sample263 && fixedFlag$sample9);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample263[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample263)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample278() {
		if(!fixedProbFlag$sample278) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((issues$var213[i$var211][3]?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample278[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample278)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample278 = (fixedFlag$sample278 && fixedFlag$sample12);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample278[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample278)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample293() {
		if(!fixedProbFlag$sample293) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((issues$var213[i$var211][4]?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample293[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample293 = (fixedFlag$sample293 && fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample293[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample3() {
		if(!fixedProbFlag$sample3) {
			double cv$distributionAccumulator = Math.log((flag1?0.01:0.99));
			logProbability$flag1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample3 = fixedFlag$sample3;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag1);
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag1);
		}
	}

	private final void logProbabilityValue$sample308() {
		if(!fixedProbFlag$sample308) {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				double cv$distributionAccumulator = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((issues$var213[i$var211][5]?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample308[i$var211] = cv$distributionAccumulator;
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample308)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample308 = (fixedFlag$sample308 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample308[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample308)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample430() {
		if(!fixedProbFlag$sample430) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(noisyOr[j])
					var402 = p13[j][0];
				else
					var402 = 0.0;
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((issues$var383[0][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				logProbability$sample430[0][j] = cv$weightedProbability;
				logProbability$n13State = (logProbability$n13State + cv$weightedProbability);
			}
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(noisyOr[j])
					var402 = p13[j][1];
				else
					var402 = 0.0;
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((issues$var383[1][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				logProbability$sample430[1][j] = cv$weightedProbability;
				logProbability$n13State = (logProbability$n13State + cv$weightedProbability);
			}
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample430 = ((((((fixedFlag$sample430 && fixedFlag$sample233) && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double cv$sampleValue = logProbability$sample430[0][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$n13State = (logProbability$n13State + cv$sampleValue);
			}
			for(int j = 0; j < 5; j += 1) {
				double cv$sampleValue = logProbability$sample430[1][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$n13State = (logProbability$n13State + cv$sampleValue);
			}
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = Math.log((flag2?0.01:0.99));
			logProbability$flag2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag2);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag2);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = Math.log((flag3?0.01:0.99));
			logProbability$flag3 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flag3);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag3);
		}
	}

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

	@Override
	public final void allocator() {
		p = new double[6][];
		p[0] = new double[5];
		p[1] = new double[5];
		p[2] = new double[5];
		p[3] = new double[5];
		p[4] = new double[5];
		p[5] = new double[5];
		noisyOr = new boolean[5];
		if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
			issues$var213 = new boolean[5][];
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				issues$var213[i$var211] = new boolean[6];
		}
		p13 = new double[5][];
		p13[0] = new double[2];
		p13[1] = new double[2];
		p13[2] = new double[2];
		p13[3] = new double[2];
		p13[4] = new double[2];
		n13State = new boolean[2];
		if(!fixedFlag$sample430) {
			issues$var383 = new boolean[2][];
			issues$var383[0] = new boolean[5];
			issues$var383[1] = new boolean[5];
		}
		constrainedFlag$sample233 = new boolean[5];
		constrainedFlag$sample248 = new boolean[5];
		constrainedFlag$sample263 = new boolean[5];
		constrainedFlag$sample278 = new boolean[5];
		constrainedFlag$sample293 = new boolean[5];
		constrainedFlag$sample308 = new boolean[5];
		logProbability$sample233 = new double[5];
		logProbability$sample248 = new double[5];
		logProbability$sample263 = new double[5];
		logProbability$sample278 = new double[5];
		logProbability$sample293 = new double[5];
		logProbability$sample308 = new double[5];
		logProbability$sample430 = new double[2][];
		logProbability$sample430[0] = new double[5];
		logProbability$sample430[1] = new double[5];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!fixedFlag$sample233) {
							double var223;
							if(flag1)
								var223 = p[0][i$var211];
							else
								var223 = 0.0;
							issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!fixedFlag$sample248) {
							double var236;
							if(flag2)
								var236 = p[1][i$var211];
							else
								var236 = 0.0;
							issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!fixedFlag$sample263) {
							double var249;
							if(flag3)
								var249 = p[2][i$var211];
							else
								var249 = 0.0;
							issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!fixedFlag$sample278) {
							double var262;
							if(flag4)
								var262 = p[3][i$var211];
							else
								var262 = 0.0;
							issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!fixedFlag$sample293) {
							double var275;
							if(flag5)
								var275 = p[4][i$var211];
							else
								var275 = 0.0;
							issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!fixedFlag$sample308) {
							double var288;
							if(flag6)
								var288 = p[5][i$var211];
							else
								var288 = 0.0;
							issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
							boolean reduceVar$var300$42 = false;
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								reduceVar$var300$42 = (reduceVar$var300$42 || issues$var213[i$var211][cv$reduction313Index]);
							noisyOr[i$var211] = reduceVar$var300$42;
						}
					}
			}
		);
		if(!fixedFlag$sample430)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(noisyOr[j])
												var402 = p13[j][i$var381];
											else
												var402 = 0.0;
											issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							boolean reduceVar$var414$8 = false;
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								reduceVar$var414$8 = (reduceVar$var414$8 || issues$var383[i$var381][cv$reduction435Index]);
							n13State[i$var381] = reduceVar$var414$8;
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!fixedFlag$sample233) {
							double var223;
							if(flag1)
								var223 = p[0][i$var211];
							else
								var223 = 0.0;
							issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!fixedFlag$sample248) {
							double var236;
							if(flag2)
								var236 = p[1][i$var211];
							else
								var236 = 0.0;
							issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!fixedFlag$sample263) {
							double var249;
							if(flag3)
								var249 = p[2][i$var211];
							else
								var249 = 0.0;
							issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!fixedFlag$sample278) {
							double var262;
							if(flag4)
								var262 = p[3][i$var211];
							else
								var262 = 0.0;
							issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!fixedFlag$sample293) {
							double var275;
							if(flag5)
								var275 = p[4][i$var211];
							else
								var275 = 0.0;
							issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!fixedFlag$sample308) {
							double var288;
							if(flag6)
								var288 = p[5][i$var211];
							else
								var288 = 0.0;
							issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$46 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$46 = (reduceVar$var300$46 || issues$var213[i$var211][cv$reduction313Index]);
						noisyOr[i$var211] = reduceVar$var300$46;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(noisyOr[j])
												var402 = p13[j][i$var381];
											else
												var402 = 0.0;
											issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$12 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$12 = (reduceVar$var414$12 || issues$var383[i$var381][cv$reduction435Index]);
						n13State[i$var381] = reduceVar$var414$12;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!fixedFlag$sample233) {
							double var223;
							if(flag1)
								var223 = p[0][i$var211];
							else
								var223 = 0.0;
							issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!fixedFlag$sample248) {
							double var236;
							if(flag2)
								var236 = p[1][i$var211];
							else
								var236 = 0.0;
							issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!fixedFlag$sample263) {
							double var249;
							if(flag3)
								var249 = p[2][i$var211];
							else
								var249 = 0.0;
							issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!fixedFlag$sample278) {
							double var262;
							if(flag4)
								var262 = p[3][i$var211];
							else
								var262 = 0.0;
							issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!fixedFlag$sample293) {
							double var275;
							if(flag5)
								var275 = p[4][i$var211];
							else
								var275 = 0.0;
							issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!fixedFlag$sample308) {
							double var288;
							if(flag6)
								var288 = p[5][i$var211];
							else
								var288 = 0.0;
							issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$43 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$43 = (reduceVar$var300$43 || issues$var213[i$var211][cv$reduction313Index]);
						noisyOr[i$var211] = reduceVar$var300$43;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(noisyOr[j])
												var402 = p13[j][i$var381];
											else
												var402 = 0.0;
											issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$9 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$9 = (reduceVar$var414$9 || issues$var383[i$var381][cv$reduction435Index]);
						n13State[i$var381] = reduceVar$var414$9;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!fixedFlag$sample233) {
							double var223;
							if(flag1)
								var223 = p[0][i$var211];
							else
								var223 = 0.0;
							issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!fixedFlag$sample248) {
							double var236;
							if(flag2)
								var236 = p[1][i$var211];
							else
								var236 = 0.0;
							issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!fixedFlag$sample263) {
							double var249;
							if(flag3)
								var249 = p[2][i$var211];
							else
								var249 = 0.0;
							issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!fixedFlag$sample278) {
							double var262;
							if(flag4)
								var262 = p[3][i$var211];
							else
								var262 = 0.0;
							issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!fixedFlag$sample293) {
							double var275;
							if(flag5)
								var275 = p[4][i$var211];
							else
								var275 = 0.0;
							issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!fixedFlag$sample308) {
							double var288;
							if(flag6)
								var288 = p[5][i$var211];
							else
								var288 = 0.0;
							issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
							boolean reduceVar$var300$44 = false;
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								reduceVar$var300$44 = (reduceVar$var300$44 || issues$var213[i$var211][cv$reduction313Index]);
							noisyOr[i$var211] = reduceVar$var300$44;
						}
					}
			}
		);
		if(!fixedFlag$sample430)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(noisyOr[j])
												var402 = p13[j][i$var381];
											else
												var402 = 0.0;
											issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							boolean reduceVar$var414$10 = false;
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								reduceVar$var414$10 = (reduceVar$var414$10 || issues$var383[i$var381][cv$reduction435Index]);
							n13State[i$var381] = reduceVar$var414$10;
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!fixedFlag$sample233) {
							double var223;
							if(flag1)
								var223 = p[0][i$var211];
							else
								var223 = 0.0;
							issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						if(!fixedFlag$sample248) {
							double var236;
							if(flag2)
								var236 = p[1][i$var211];
							else
								var236 = 0.0;
							issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						if(!fixedFlag$sample263) {
							double var249;
							if(flag3)
								var249 = p[2][i$var211];
							else
								var249 = 0.0;
							issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						if(!fixedFlag$sample278) {
							double var262;
							if(flag4)
								var262 = p[3][i$var211];
							else
								var262 = 0.0;
							issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						if(!fixedFlag$sample293) {
							double var275;
							if(flag5)
								var275 = p[4][i$var211];
							else
								var275 = 0.0;
							issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						if(!fixedFlag$sample308) {
							double var288;
							if(flag6)
								var288 = p[5][i$var211];
							else
								var288 = 0.0;
							issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						boolean reduceVar$var300$45 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$45 = (reduceVar$var300$45 || issues$var213[i$var211][cv$reduction313Index]);
						noisyOr[i$var211] = reduceVar$var300$45;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						if(!fixedFlag$sample430)
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											double var402;
											if(noisyOr[j])
												var402 = p13[j][i$var381];
											else
												var402 = 0.0;
											issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						boolean reduceVar$var414$11 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$11 = (reduceVar$var414$11 || issues$var383[i$var381][cv$reduction435Index]);
						n13State[i$var381] = reduceVar$var414$11;
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample3)
				inferSample3();
			if(!fixedFlag$sample6)
				inferSample6();
			if(!fixedFlag$sample9)
				inferSample9();
			if(!fixedFlag$sample12)
				inferSample12();
			if(!fixedFlag$sample15)
				inferSample15();
			if(!fixedFlag$sample18)
				inferSample18();
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample18)
				inferSample18();
			if(!fixedFlag$sample15)
				inferSample15();
			if(!fixedFlag$sample12)
				inferSample12();
			if(!fixedFlag$sample9)
				inferSample9();
			if(!fixedFlag$sample6)
				inferSample6();
			if(!fixedFlag$sample3)
				inferSample3();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample3)
			drawValueSample3();
		if(!constrainedFlag$sample6)
			drawValueSample6();
		if(!constrainedFlag$sample9)
			drawValueSample9();
		if(!constrainedFlag$sample12)
			drawValueSample12();
		if(!constrainedFlag$sample15)
			drawValueSample15();
		if(!constrainedFlag$sample18)
			drawValueSample18();
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!constrainedFlag$sample233[i$var211])
							drawValueSample233(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample248[i$var211])
							drawValueSample248(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample263[i$var211])
							drawValueSample263(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample278[i$var211])
							drawValueSample278(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample293[i$var211])
							drawValueSample293(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample308[i$var211])
							drawValueSample308(i$var211, threadID$i$var211, RNG$1);
					}
			}
		);
		if(!fixedFlag$sample430)
			parallelFor(RNG$, 0, 2, 1,
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
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample3)
			logProbability$flag1 = Double.NaN;
		if(!fixedProbFlag$sample6)
			logProbability$flag2 = Double.NaN;
		if(!fixedProbFlag$sample9)
			logProbability$flag3 = Double.NaN;
		if(!fixedProbFlag$sample12)
			logProbability$flag4 = Double.NaN;
		if(!fixedProbFlag$sample15)
			logProbability$flag5 = Double.NaN;
		if(!fixedProbFlag$sample18)
			logProbability$flag6 = Double.NaN;
		logProbability$issues$var213 = 0.0;
		logProbability$noisyOr = 0.0;
		if(!fixedProbFlag$sample233) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample233[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample248[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample263[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample278[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample293[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample308[i$var211] = Double.NaN;
		}
		logProbability$issues$var383 = 0.0;
		logProbability$n13State = 0.0;
		if(!fixedProbFlag$sample430) {
			for(int j = 0; j < 5; j += 1)
				logProbability$sample430[0][j] = Double.NaN;
			for(int j = 0; j < 5; j += 1)
				logProbability$sample430[1][j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		double[] var23 = p[0];
		var23[0] = 0.0;
		var23[1] = 1.0;
		var23[2] = 0.0;
		var23[3] = 0.0;
		var23[4] = 0.0;
		double[] var53 = p[1];
		var53[0] = 0.5;
		var53[1] = 0.5;
		var53[2] = 0.0;
		var53[3] = 0.0;
		var53[4] = 0.0;
		double[] var81 = p[2];
		var81[0] = 0.0;
		var81[1] = 0.0;
		var81[2] = 0.0;
		var81[3] = 1.0;
		var81[4] = 0.0;
		double[] var111 = p[3];
		var111[0] = 0.0;
		var111[1] = 0.0;
		var111[2] = 0.0;
		var111[3] = 1.0;
		var111[4] = 0.0;
		double[] var141 = p[4];
		var141[0] = 0.0;
		var141[1] = 0.0;
		var141[2] = 1.0;
		var141[3] = 0.0;
		var141[4] = 0.0;
		double[] var171 = p[5];
		var171[0] = 0.0;
		var171[1] = 0.0;
		var171[2] = 1.0;
		var171[3] = 0.0;
		var171[4] = 0.0;
		double[] var306 = p13[0];
		var306[0] = 0.1;
		var306[1] = 0.9;
		double[] var319 = p13[1];
		var319[0] = 1.0;
		var319[1] = 0.0;
		double[] var332 = p13[2];
		var332[0] = 0.5;
		var332[1] = 0.5;
		double[] var345 = p13[3];
		var345[0] = 0.5;
		var345[1] = 0.5;
		double[] var358 = p13[4];
		var358[0] = 0.0;
		var358[1] = 1.0;
		for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
			constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
		for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
			constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
		for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
			constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
		for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
			constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
		for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
			constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
		for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
			constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample3)
			logProbabilityValue$sample3();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(fixedFlag$sample233)
			logProbabilityValue$sample233();
		if(fixedFlag$sample248)
			logProbabilityValue$sample248();
		if(fixedFlag$sample263)
			logProbabilityValue$sample263();
		if(fixedFlag$sample278)
			logProbabilityValue$sample278();
		if(fixedFlag$sample293)
			logProbabilityValue$sample293();
		if(fixedFlag$sample308)
			logProbabilityValue$sample308();
		if(fixedFlag$sample430)
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
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						boolean reduceVar$var300$47 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							reduceVar$var300$47 = (reduceVar$var300$47 || issues$var213[i$var211][cv$reduction313Index]);
						noisyOr[i$var211] = reduceVar$var300$47;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var381, int forEnd$i$var381, int threadID$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var381 = forStart$i$var381; i$var381 < forEnd$i$var381; i$var381 += 1) {
						boolean reduceVar$var414$13 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							reduceVar$var414$13 = (reduceVar$var414$13 || issues$var383[i$var381][cv$reduction435Index]);
						n13State[i$var381] = reduceVar$var414$13;
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