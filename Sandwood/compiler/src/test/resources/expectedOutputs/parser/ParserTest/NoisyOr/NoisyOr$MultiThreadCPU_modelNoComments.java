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
	private double prior1;
	private double prior2;
	private double prior3;
	private double prior4;
	private double prior5;
	private double prior6;
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
		constrainedFlag$sample12 = (fixedFlag$sample12 || constrainedFlag$sample12);
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		fixedProbFlag$sample278 = (fixedFlag$sample12 && fixedProbFlag$sample278);
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value, boolean allocated$) {
		fixedFlag$sample15 = cv$value;
		constrainedFlag$sample15 = (fixedFlag$sample15 || constrainedFlag$sample15);
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		fixedProbFlag$sample293 = (fixedFlag$sample15 && fixedProbFlag$sample293);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value, boolean allocated$) {
		fixedFlag$sample18 = cv$value;
		constrainedFlag$sample18 = (fixedFlag$sample18 || constrainedFlag$sample18);
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		fixedProbFlag$sample308 = (fixedFlag$sample18 && fixedProbFlag$sample308);
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
				constrainedFlag$sample233[index$constrainedFlag$sample233$1] = fixedFlag$sample233;
		}
		fixedProbFlag$sample233 = (fixedFlag$sample233 && fixedProbFlag$sample233);
		fixedProbFlag$sample430 = (fixedFlag$sample233 && fixedProbFlag$sample430);
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
				constrainedFlag$sample248[index$constrainedFlag$sample248$1] = fixedFlag$sample248;
		}
		fixedProbFlag$sample248 = (fixedFlag$sample248 && fixedProbFlag$sample248);
		fixedProbFlag$sample430 = (fixedFlag$sample248 && fixedProbFlag$sample430);
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
				constrainedFlag$sample263[index$constrainedFlag$sample263$1] = fixedFlag$sample263;
		}
		fixedProbFlag$sample263 = (fixedFlag$sample263 && fixedProbFlag$sample263);
		fixedProbFlag$sample430 = (fixedFlag$sample263 && fixedProbFlag$sample430);
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
				constrainedFlag$sample278[index$constrainedFlag$sample278$1] = fixedFlag$sample278;
		}
		fixedProbFlag$sample278 = (fixedFlag$sample278 && fixedProbFlag$sample278);
		fixedProbFlag$sample430 = (fixedFlag$sample278 && fixedProbFlag$sample430);
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
				constrainedFlag$sample293[index$constrainedFlag$sample293$1] = fixedFlag$sample293;
		}
		fixedProbFlag$sample293 = (fixedFlag$sample293 && fixedProbFlag$sample293);
		fixedProbFlag$sample430 = (fixedFlag$sample293 && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample3() {
		return fixedFlag$sample3;
	}

	@Override
	public final void set$fixedFlag$sample3(boolean cv$value, boolean allocated$) {
		fixedFlag$sample3 = cv$value;
		constrainedFlag$sample3 = (fixedFlag$sample3 || constrainedFlag$sample3);
		fixedProbFlag$sample3 = (fixedFlag$sample3 && fixedProbFlag$sample3);
		fixedProbFlag$sample233 = (fixedFlag$sample3 && fixedProbFlag$sample233);
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
				constrainedFlag$sample308[index$constrainedFlag$sample308$1] = fixedFlag$sample308;
		}
		fixedProbFlag$sample308 = (fixedFlag$sample308 && fixedProbFlag$sample308);
		fixedProbFlag$sample430 = (fixedFlag$sample308 && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample430() {
		return fixedFlag$sample430;
	}

	@Override
	public final void set$fixedFlag$sample430(boolean cv$value, boolean allocated$) {
		fixedFlag$sample430 = cv$value;
		fixedProbFlag$sample430 = (fixedFlag$sample430 && fixedProbFlag$sample430);
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
		fixedFlag$sample6 = cv$value;
		constrainedFlag$sample6 = (fixedFlag$sample6 || constrainedFlag$sample6);
		fixedProbFlag$sample6 = (fixedFlag$sample6 && fixedProbFlag$sample6);
		fixedProbFlag$sample248 = (fixedFlag$sample6 && fixedProbFlag$sample248);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
		fixedFlag$sample9 = cv$value;
		constrainedFlag$sample9 = (fixedFlag$sample9 || constrainedFlag$sample9);
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		fixedProbFlag$sample263 = (fixedFlag$sample9 && fixedProbFlag$sample263);
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
		return prior1;
	}

	@Override
	public final double get$prior2() {
		return prior2;
	}

	@Override
	public final double get$prior3() {
		return prior3;
	}

	@Override
	public final double get$prior4() {
		return prior4;
	}

	@Override
	public final double get$prior5() {
		return prior5;
	}

	@Override
	public final double get$prior6() {
		return prior6;
	}

	private final void drawValueSample12() {
		flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
	}

	private final void drawValueSample15() {
		flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
	}

	private final void drawValueSample18() {
		flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
	}

	private final void drawValueSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var223;
		if(flag1)
			var223 = p[0][i$var211];
		else
			var223 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
		{
			{
				if(((0 <= 0) && (0 < 6))) {
					{
						boolean reduceVar$var300$36 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$36;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$36 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$36;
					}
				}
			}
		}
	}

	private final void drawValueSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var236;
		if(flag2)
			var236 = p[1][i$var211];
		else
			var236 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
		{
			{
				if(((0 <= 1) && (1 < 6))) {
					{
						boolean reduceVar$var300$37 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$37;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$37 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$37;
					}
				}
			}
		}
	}

	private final void drawValueSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var249;
		if(flag3)
			var249 = p[2][i$var211];
		else
			var249 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
		{
			{
				if(((0 <= 2) && (2 < 6))) {
					{
						boolean reduceVar$var300$38 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$38;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$38 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$38;
					}
				}
			}
		}
	}

	private final void drawValueSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var262;
		if(flag4)
			var262 = p[3][i$var211];
		else
			var262 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
		{
			{
				if(((0 <= 3) && (3 < 6))) {
					{
						boolean reduceVar$var300$39 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$39;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$39 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$39;
					}
				}
			}
		}
	}

	private final void drawValueSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var275;
		if(flag5)
			var275 = p[4][i$var211];
		else
			var275 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
		{
			{
				if(((0 <= 4) && (4 < 6))) {
					{
						boolean reduceVar$var300$40 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$40;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$40 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$40;
					}
				}
			}
		}
	}

	private final void drawValueSample3() {
		flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
	}

	private final void drawValueSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var288;
		if(flag6)
			var288 = p[5][i$var211];
		else
			var288 = 0.0;
		issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
		{
			{
				if(((0 <= 5) && (5 < 6))) {
					{
						boolean reduceVar$var300$41 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$41;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$41 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$41;
					}
				}
			}
		}
	}

	private final void drawValueSample430(int i$var381, int j, int threadID$cv$j, Rng RNG$) {
		double var402;
		if(noisyOr[j])
			var402 = p13[j][i$var381];
		else
			var402 = 0.0;
		issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		{
			{
				if(((0 <= j) && (j < 5))) {
					{
						boolean reduceVar$var414$7 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$7;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$7 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$7;
					}
				}
			}
		}
	}

	private final void drawValueSample6() {
		flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
	}

	private final void drawValueSample9() {
		flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
	}

	private final void inferSample12() {
		if(true) {
			constrainedFlag$sample12 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var12$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag4 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior4) && (prior4 <= 1.0))?Math.log((cv$currentValue?prior4:(1.0 - prior4))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var262$2_1 = p[3][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample278 || constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample12 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var262$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample278 || constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample12 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample12) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag4 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample15() {
		if(true) {
			constrainedFlag$sample15 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var15$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag5 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior5) && (prior5 <= 1.0))?Math.log((cv$currentValue?prior5:(1.0 - prior5))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var275$2_1 = p[4][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample293 || constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample15 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var275$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample293 || constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample15 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample15) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag5 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample18() {
		if(true) {
			constrainedFlag$sample18 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var18$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag6 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior6) && (prior6 <= 1.0))?Math.log((cv$currentValue?prior6:(1.0 - prior6))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var288$2_1 = p[5][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample308 || constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample18 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var288$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample308 || constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample18 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample18) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag6 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample233[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var225$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var225 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][0] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								boolean reduceVar$var300$24 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$24;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$24 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$24;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var223;
					if(flag1)
						var223 = p[0][i$var211];
					else
						var223 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$currentValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 0) && (0 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample233[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var225 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][0] = var225;
						}
					}
				}
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								boolean reduceVar$var300$25 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$25;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$25 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$25;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample248[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var238$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var238 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][1] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								boolean reduceVar$var300$26 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$26;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$26 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$26;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var236;
					if(flag2)
						var236 = p[1][i$var211];
					else
						var236 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$currentValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 1) && (1 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample248[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var238 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][1] = var238;
						}
					}
				}
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								boolean reduceVar$var300$27 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$27;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$27 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$27;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample263[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var251$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var251 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][2] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								boolean reduceVar$var300$28 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$28;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$28 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$28;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var249;
					if(flag3)
						var249 = p[2][i$var211];
					else
						var249 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$currentValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 2) && (2 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample263[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var251 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][2] = var251;
						}
					}
				}
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								boolean reduceVar$var300$29 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$29;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$29 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$29;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample278[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var264$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var264 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][3] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								boolean reduceVar$var300$30 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$30;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$30 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$30;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var262;
					if(flag4)
						var262 = p[3][i$var211];
					else
						var262 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$currentValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 3) && (3 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample278[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var264 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][3] = var264;
						}
					}
				}
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								boolean reduceVar$var300$31 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$31;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$31 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$31;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample293[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var277$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var277 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][4] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								boolean reduceVar$var300$32 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$32;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$32 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$32;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var275;
					if(flag5)
						var275 = p[4][i$var211];
					else
						var275 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$currentValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 4) && (4 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample293[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var277 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][4] = var277;
						}
					}
				}
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								boolean reduceVar$var300$33 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$33;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$33 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$33;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample3() {
		if(true) {
			constrainedFlag$sample3 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var3$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag1 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior1) && (prior1 <= 1.0))?Math.log((cv$currentValue?prior1:(1.0 - prior1))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var223$2_1 = p[0][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample233 || constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample3 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var223$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample233 || constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample3 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample3) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag1 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample308[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var290$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var290 = cv$currentValue;
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][5] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								boolean reduceVar$var300$34 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$34;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$34 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$34;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var288;
					if(flag6)
						var288 = p[5][i$var211];
					else
						var288 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$currentValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 5) && (5 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
												{
													{
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
														}
													}
												}
											}
										}
									}
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
			if(constrainedFlag$sample308[((i$var211 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				boolean var290 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][5] = var290;
						}
					}
				}
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								boolean reduceVar$var300$35 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$35;
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$35 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$35;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample6() {
		if(true) {
			constrainedFlag$sample6 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var6$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag2 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior2) && (prior2 <= 1.0))?Math.log((cv$currentValue?prior2:(1.0 - prior2))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var236$2_1 = p[1][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample248 || constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample6 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var236$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample248 || constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample6 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample6) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag2 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample9() {
		if(true) {
			constrainedFlag$sample9 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				flag3 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior3) && (prior3 <= 1.0))?Math.log((cv$currentValue?prior3:(1.0 - prior3))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var249$2_1 = p[2][i$var211];
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample263 || constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample9 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
									{
										{
											if(!cv$currentValue) {
												double traceTempVariable$var249$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample263 || constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample9 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
											}
										}
									}
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
			if(constrainedFlag$sample9) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				flag3 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag4;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior4) && (prior4 <= 1.0))?Math.log((cv$sampleValue?prior4:(1.0 - prior4))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag4 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag4;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag5;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior5) && (prior5 <= 1.0))?Math.log((cv$sampleValue?prior5:(1.0 - prior5))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag5 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag5;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag6;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior6) && (prior6 <= 1.0))?Math.log((cv$sampleValue?prior6:(1.0 - prior6))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag6 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample233() {
		if(!fixedProbFlag$sample233) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][0];
						{
							{
								double var223;
								if(flag1)
									var223 = p[0][i$var211];
								else
									var223 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$sampleValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample233[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample233)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample233 = (fixedFlag$sample233 && fixedFlag$sample3);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample233[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][1];
						{
							{
								double var236;
								if(flag2)
									var236 = p[1][i$var211];
								else
									var236 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$sampleValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample248[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample248)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample248 = (fixedFlag$sample248 && fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample248[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][2];
						{
							{
								double var249;
								if(flag3)
									var249 = p[2][i$var211];
								else
									var249 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$sampleValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample263[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample263)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample263 = (fixedFlag$sample263 && fixedFlag$sample9);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample263[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][3];
						{
							{
								double var262;
								if(flag4)
									var262 = p[3][i$var211];
								else
									var262 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$sampleValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample278[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample278)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample278 = (fixedFlag$sample278 && fixedFlag$sample12);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample278[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][4];
						{
							{
								double var275;
								if(flag5)
									var275 = p[4][i$var211];
								else
									var275 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$sampleValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample293[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample293 = (fixedFlag$sample293 && fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample293[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample3() {
		if(!fixedProbFlag$sample3) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior1) && (prior1 <= 1.0))?Math.log((cv$sampleValue?prior1:(1.0 - prior1))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample3 = fixedFlag$sample3;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample308() {
		if(!fixedProbFlag$sample308) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][5];
						{
							{
								double var288;
								if(flag6)
									var288 = p[5][i$var211];
								else
									var288 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$sampleValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample308[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample308)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample308 = (fixedFlag$sample308 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample308[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = issues$var383[((i$var381 - 0) / 1)][j];
							{
								{
									double var402;
									if(noisyOr[j])
										var402 = p13[j][i$var381];
									else
										var402 = 0.0;
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var402) && (var402 <= 1.0))?Math.log((cv$sampleValue?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$n13State = false;
					{
						{
							if(((0 <= j) && (j < 5))) {
								if(!cv$guard$n13State) {
									cv$guard$n13State = true;
									logProbability$n13State = (logProbability$n13State + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample430 = ((((((fixedFlag$sample430 && fixedFlag$sample233) && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					boolean cv$guard$n13State = false;
					{
						{
							if(((0 <= j) && (j < 5))) {
								if(!cv$guard$n13State) {
									cv$guard$n13State = true;
									logProbability$n13State = (logProbability$n13State + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag2;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior2) && (prior2 <= 1.0))?Math.log((cv$sampleValue?prior2:(1.0 - prior2))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag2 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flag3;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior3) && (prior3 <= 1.0))?Math.log((cv$sampleValue?prior3:(1.0 - prior3))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$flag3 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var3$stateProbabilityGlobal = new double[2];
		}
		{
			cv$var6$stateProbabilityGlobal = new double[2];
		}
		{
			cv$var9$stateProbabilityGlobal = new double[2];
		}
		{
			cv$var12$stateProbabilityGlobal = new double[2];
		}
		{
			cv$var15$stateProbabilityGlobal = new double[2];
		}
		{
			cv$var18$stateProbabilityGlobal = new double[2];
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var225$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var225$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var238$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var238$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var251$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var251$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var264$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var264$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var277$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var277$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var290$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var290$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			p = new double[6][];
			p[0] = new double[5];
			p[1] = new double[5];
			p[2] = new double[5];
			p[3] = new double[5];
			p[4] = new double[5];
			p[5] = new double[5];
		}
		{
			noisyOr = new boolean[5];
		}
		if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
			{
				issues$var213 = new boolean[((((5 - 1) - 0) / 1) + 1)][];
				for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
					issues$var213[((i$var211 - 0) / 1)] = new boolean[6];
			}
		}
		{
			p13 = new double[5][];
			p13[0] = new double[2];
			p13[1] = new double[2];
			p13[2] = new double[2];
			p13[3] = new double[2];
			p13[4] = new double[2];
		}
		{
			n13State = new boolean[2];
		}
		if(!fixedFlag$sample430) {
			{
				issues$var383 = new boolean[((((2 - 1) - 0) / 1) + 1)][];
				for(int i$var381 = 0; i$var381 < 2; i$var381 += 1)
					issues$var383[((i$var381 - 0) / 1)] = new boolean[5];
			}
		}
		{
			constrainedFlag$sample233 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample248 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample263 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample278 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample293 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample308 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample233 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample248 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample263 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample278 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample293 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample308 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample430 = new double[((((2 - 1) - 0) / 1) + 1)][];
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1)
				logProbability$sample430[((i$var381 - 0) / 1)] = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						boolean reduceVar$var300$42 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$42;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
								reduceVar$var300$42 = (x$var297 || y$var298);
						}
						if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
							noisyOr[i$var211] = reduceVar$var300$42;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						boolean reduceVar$var414$8 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$8;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							if(!fixedFlag$sample430)
								reduceVar$var414$8 = (x$var411 || y$var412);
						}
						if(!fixedFlag$sample430)
							n13State[i$var381] = reduceVar$var414$8;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						boolean reduceVar$var300$46 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$46;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$46 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$46;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						boolean reduceVar$var414$12 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$12;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$12 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$12;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						boolean reduceVar$var300$43 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$43;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$43 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$43;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						boolean reduceVar$var414$9 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$9;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$9 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$9;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						boolean reduceVar$var300$44 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$44;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
								reduceVar$var300$44 = (x$var297 || y$var298);
						}
						if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
							noisyOr[i$var211] = reduceVar$var300$44;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						boolean reduceVar$var414$10 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$10;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							if(!fixedFlag$sample430)
								reduceVar$var414$10 = (x$var411 || y$var412);
						}
						if(!fixedFlag$sample430)
							n13State[i$var381] = reduceVar$var414$10;
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						boolean reduceVar$var300$45 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$45;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$45 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$45;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						boolean reduceVar$var414$11 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$11;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$11 = (x$var411 || y$var412);
						}
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
						if(!constrainedFlag$sample233[((i$var211 - 0) / 1)])
							drawValueSample233(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample248[((i$var211 - 0) / 1)])
							drawValueSample248(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample263[((i$var211 - 0) / 1)])
							drawValueSample263(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample278[((i$var211 - 0) / 1)])
							drawValueSample278(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample293[((i$var211 - 0) / 1)])
							drawValueSample293(i$var211, threadID$i$var211, RNG$1);
						if(!constrainedFlag$sample308[((i$var211 - 0) / 1)])
							drawValueSample308(i$var211, threadID$i$var211, RNG$1);
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample430)
											drawValueSample430(i$var381, j, threadID$j, RNG$2);
									}
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
				logProbability$sample233[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample248[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample263[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample278[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample293[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample308[((i$var211 - 0) / 1)] = Double.NaN;
		}
		logProbability$issues$var383 = 0.0;
		logProbability$n13State = 0.0;
		if(!fixedProbFlag$sample430) {
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1)
					logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		prior1 = 0.01;
		prior2 = 0.01;
		prior3 = 0.01;
		prior4 = 0.01;
		prior5 = 0.01;
		prior6 = 0.01;
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
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$47;
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$47 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$47;
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var381, int forEnd$i$var381, int threadID$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var381 = forStart$i$var381; i$var381 < forEnd$i$var381; i$var381 += 1) {
						boolean reduceVar$var414$13 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$13;
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$13 = (x$var411 || y$var412);
						}
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