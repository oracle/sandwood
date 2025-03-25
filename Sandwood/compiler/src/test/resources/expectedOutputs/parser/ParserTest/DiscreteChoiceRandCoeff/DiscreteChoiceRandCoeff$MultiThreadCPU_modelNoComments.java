package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private double[][] exped;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean[] guard$sample21categorical102$global;
	private boolean[][] guard$sample21put101$global;
	private boolean[][] guard$sample47categorical102$global;
	private boolean[][][] guard$sample47put101$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample103;
	private double[] logProbability$sample21;
	private double[] logProbability$sample47;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var101;
	private double logProbability$var27;
	private double logProbability$var33;
	private double logProbability$var35;
	private double logProbability$var9;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	@Override
	public final void set$ObsChoices(int[] cv$value) {
		ObsChoices = cv$value;
	}

	@Override
	public final int[][] get$Prices() {
		return Prices;
	}

	@Override
	public final void set$Prices(int[][] cv$value) {
		Prices = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value) {
		beta = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample47 = (fixedFlag$sample28 && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample47 = (fixedFlag$sample34 && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedProbFlag$sample103);
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
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	@Override
	public final int get$noObs() {
		return noObs;
	}

	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	@Override
	public final double[][] get$prob() {
		return prob;
	}

	@Override
	public final double get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double cv$value) {
		sigma = cv$value;
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample103 = false;
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = choices[i];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[((i - 0) / 1)][cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var101[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample103[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var20];
					{
						{
							double var7 = 0.0;
							double var8 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var7) / Math.sqrt(var8))) - (0.5 * Math.log(var8))));
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
				logProbability$sample21[((var20 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									logProbability$exped = (logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!cv$guard$prob) {
												cv$guard$prob = true;
												logProbability$prob = (logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									for(int i = 0; i < noObs; i += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$sampleValue = logProbability$sample21[((var20 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									logProbability$exped = (logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!cv$guard$prob) {
												cv$guard$prob = true;
												logProbability$prob = (logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									for(int i = 0; i < noObs; i += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = b;
				{
					{
						double var25 = 0.0;
						double var26 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var25) / Math.sqrt(var26))) - (0.5 * Math.log(var26))));
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
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$b = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = sigma;
				{
					{
						double var31 = 2.0;
						double var32 = 2.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var31, var32));
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
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$sigma = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var33 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = beta[var46];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - b) / Math.sqrt(sigma))) - (0.5 * Math.log(sigma))));
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
				logProbability$sample47[((var46 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									logProbability$exped = (logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!cv$guard$prob) {
												cv$guard$prob = true;
												logProbability$prob = (logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$beta = (logProbability$beta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = ((fixedFlag$sample47 && fixedFlag$sample28) && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$sampleValue = logProbability$sample47[((var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									logProbability$exped = (logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!cv$guard$prob) {
												cv$guard$prob = true;
												logProbability$prob = (logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var35 = cv$rvAccumulator;
			logProbability$beta = (logProbability$beta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample21(int var20) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = ut[var20];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var21 = cv$proposedValue;
					{
						{
							ut[var20] = cv$currentValue;
						}
					}
					{
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1)
									exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						}
					}
					{
						boolean[][] guard$sample21put101 = guard$sample21put101$global;
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
												guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < noObs; i += 1)
											guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
												if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
													{
														double reduceVar$sum$15 = 0.0;
														for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
															double k = reduceVar$sum$15;
															double l = exped[((i - 0) / 1)][cv$reduction82Index];
															reduceVar$sum$15 = (k + l);
														}
														prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$15);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < noObs; i += 1) {
											if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
												guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
												{
													double reduceVar$sum$16 = 0.0;
													for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
														double k = reduceVar$sum$16;
														double l = exped[((i - 0) / 1)][cv$reduction82Index];
														reduceVar$sum$16 = (k + l);
													}
													prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$16);
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
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var7;
				{
					cv$temp$0$var7 = 0.0;
				}
				double cv$temp$1$var8;
				{
					cv$temp$1$var8 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var7) / Math.sqrt(cv$temp$1$var8))) - (0.5 * Math.log(cv$temp$1$var8))));
				{
					{
						boolean[] guard$sample21categorical102 = guard$sample21categorical102$global;
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											guard$sample21categorical102[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < noObs; i += 1)
											guard$sample21categorical102[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var70$9_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$k$9_4 = Math.exp((traceTempVariable$var70$9_1 - (beta[i] * Prices[i][j$var69])));
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$17 = 0.0;
												for(int cv$reduction1477Index = 0; cv$reduction1477Index < j$var69; cv$reduction1477Index += 1) {
													double k = reduceVar$sum$17;
													double l = exped[((i - 0) / 1)][cv$reduction1477Index];
													reduceVar$sum$17 = (k + l);
												}
												for(int cv$reduction1477Index = (j$var69 + 1); cv$reduction1477Index < noProducts; cv$reduction1477Index += 1) {
													double k = reduceVar$sum$17;
													double l = exped[((i - 0) / 1)][cv$reduction1477Index];
													reduceVar$sum$17 = (k + l);
												}
												double cv$reduced82 = reduceVar$sum$17;
												reduceVar$sum$17 = (traceTempVariable$k$9_4 + cv$reduced82);
												double traceTempVariable$sum$9_5 = reduceVar$sum$17;
												if(!guard$sample21categorical102[((i - 0) / 1)]) {
													guard$sample21categorical102[((i - 0) / 1)] = true;
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob[((i - 0) / 1)];
																		}
																		int cv$temp$3$$var1122;
																		{
																			int $var1122 = noProducts;
																			cv$temp$3$$var1122 = $var1122;
																		}
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1122))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1122))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1122))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1122))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1122))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
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
										}
									}
								}
							}
						}
						double traceTempVariable$var70$10_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$var98$10_4 = Math.exp((traceTempVariable$var70$10_1 - (beta[i] * Prices[i][j$var69])));
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											if(!guard$sample21categorical102[((i - 0) / 1)]) {
												guard$sample21categorical102[((i - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$4$prob;
																	{
																		cv$temp$4$prob = prob[((i - 0) / 1)];
																	}
																	int cv$temp$5$$var1125;
																	{
																		int $var1125 = noProducts;
																		cv$temp$5$$var1125 = $var1125;
																	}
																	if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1125))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1125))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1125))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1125))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1125))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
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
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var21 = cv$originalValue;
			{
				{
					ut[var20] = var21;
				}
			}
			{
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < noObs; i += 1)
							exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
					}
				}
			}
			{
				boolean[][] guard$sample21put101 = guard$sample21put101$global;
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < noProducts))) {
								{
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < noObs; i += 1)
									guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < noProducts))) {
								{
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
											guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
											{
												double reduceVar$sum$18 = 0.0;
												for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
													double k = reduceVar$sum$18;
													double l = exped[((i - 0) / 1)][cv$reduction82Index];
													reduceVar$sum$18 = (k + l);
												}
												prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$18);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < noObs; i += 1) {
									if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$19 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$19;
												double l = exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$19 = (k + l);
											}
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$19);
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

	private final void sample28() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					{
						for(int var46 = 0; var46 < noObs; var46 += 1) {
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							cv$sum = (cv$sum + (cv$denominator * (beta[var46] - cv$numerator)));
							if(cv$sigmaNotFound) {
								cv$sigmaValue = sigma;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
		}
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample34() {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					{
						for(int var46 = 0; var46 < noObs; var46 += 1) {
							double cv$var35$mu = b;
							double cv$var35$diff = (cv$var35$mu - beta[var46]);
							cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
							cv$count = (cv$count + 1);
						}
					}
				}
			}
		}
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void sample47(int var46, int threadID$cv$var46, Rng RNG$) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = beta[var46];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var47 = cv$proposedValue;
					{
						{
							beta[var46] = cv$currentValue;
						}
					}
					{
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
									exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						}
					}
					{
						boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
												guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97))
											guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
												if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
													{
														double reduceVar$sum$20 = 0.0;
														for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
															double k = reduceVar$sum$20;
															double l = exped[((i - 0) / 1)][cv$reduction82Index];
															reduceVar$sum$20 = (k + l);
														}
														prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$20);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
												guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
												{
													double reduceVar$sum$21 = 0.0;
													for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
														double k = reduceVar$sum$21;
														double l = exped[((i - 0) / 1)][cv$reduction82Index];
														reduceVar$sum$21 = (k + l);
													}
													prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$21);
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
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$b;
				{
					cv$temp$0$b = b;
				}
				double cv$temp$1$sigma;
				{
					cv$temp$1$sigma = sigma;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$b) / Math.sqrt(cv$temp$1$sigma))) - (0.5 * Math.log(cv$temp$1$sigma))));
				{
					{
						boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											guard$sample47categorical102[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97))
											guard$sample47categorical102[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var71$9_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									double traceTempVariable$k$9_4 = Math.exp((ut[j$var69] - (traceTempVariable$var71$9_1 * Prices[i][j$var69])));
									if(((0 <= j$var69) && (j$var69 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$22 = 0.0;
												for(int cv$reduction1877Index = 0; cv$reduction1877Index < j$var69; cv$reduction1877Index += 1) {
													double k = reduceVar$sum$22;
													double l = exped[((i - 0) / 1)][cv$reduction1877Index];
													reduceVar$sum$22 = (k + l);
												}
												for(int cv$reduction1877Index = (j$var69 + 1); cv$reduction1877Index < noProducts; cv$reduction1877Index += 1) {
													double k = reduceVar$sum$22;
													double l = exped[((i - 0) / 1)][cv$reduction1877Index];
													reduceVar$sum$22 = (k + l);
												}
												double cv$reduced82 = reduceVar$sum$22;
												reduceVar$sum$22 = (traceTempVariable$k$9_4 + cv$reduced82);
												double traceTempVariable$sum$9_5 = reduceVar$sum$22;
												if(!guard$sample47categorical102[((i - 0) / 1)]) {
													guard$sample47categorical102[((i - 0) / 1)] = true;
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob[((i - 0) / 1)];
																		}
																		int cv$temp$3$$var1391;
																		{
																			int $var1391 = noProducts;
																			cv$temp$3$$var1391 = $var1391;
																		}
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1391))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1391))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1391))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1391))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1391))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
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
										}
									}
								}
							}
						}
						double traceTempVariable$var71$10_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
									double traceTempVariable$var98$10_4 = Math.exp((ut[j$var69] - (traceTempVariable$var71$10_1 * Prices[i][j$var69])));
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											if(!guard$sample47categorical102[((i - 0) / 1)]) {
												guard$sample47categorical102[((i - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$4$prob;
																	{
																		cv$temp$4$prob = prob[((i - 0) / 1)];
																	}
																	int cv$temp$5$$var1394;
																	{
																		int $var1394 = noProducts;
																		cv$temp$5$$var1394 = $var1394;
																	}
																	if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1394))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1394))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1394))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1394))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1394))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
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
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var47 = cv$originalValue;
			{
				{
					beta[var46] = var47;
				}
			}
			{
				for(int i = 0; i < noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
							exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
					}
				}
			}
			{
				boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
				for(int i = 0; i < noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < noProducts))) {
								{
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97))
									guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < noProducts))) {
								{
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
											guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
											{
												double reduceVar$sum$23 = 0.0;
												for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
													double k = reduceVar$sum$23;
													double l = exped[((i - 0) / 1)][cv$reduction82Index];
													reduceVar$sum$23 = (k + l);
												}
												prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$23);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$24 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$24;
												double l = exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$24 = (k + l);
											}
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$24);
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

	@Override
	public final void allocateScratch() {
		{
			int cv$max_i = 0;
			int cv$max_j$var97 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var97 = Math.max(cv$max_j$var97, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			guard$sample21put101$global = new boolean[cv$max_i][cv$max_j$var97];
		}
		{
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			guard$sample21categorical102$global = new boolean[cv$max_i];
		}
		{
			int cv$max_i = 0;
			int cv$max_j$var97 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var97 = Math.max(cv$max_j$var97, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample47put101$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
			}
		}
		{
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample47categorical102$global = new boolean[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
			}
		}
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample21) {
			{
				ut = new double[noProducts];
			}
		}
		if(!fixedFlag$sample47) {
			{
				beta = new double[noObs];
			}
		}
		{
			choices = new int[noObs];
		}
		{
			exped = new double[((((noObs - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < noObs; i += 1)
				exped[((i - 0) / 1)] = new double[noProducts];
		}
		{
			prob = new double[((((noObs - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < noObs; i += 1)
				prob[((i - 0) / 1)] = new double[noProducts];
		}
		{
			logProbability$sample21 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample47 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var101 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample103 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
									}
							}
						);
						double reduceVar$sum$25 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							double k = reduceVar$sum$25;
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								reduceVar$sum$25 = (k + l);
						}
						double reduceVar$sum$25$1 = reduceVar$sum$25;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$25$1);
									}
							}
						);
						choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[((i - 0) / 1)], noProducts);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
									}
							}
						);
						double reduceVar$sum$27 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							double k = reduceVar$sum$27;
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								reduceVar$sum$27 = (k + l);
						}
						double reduceVar$sum$27$1 = reduceVar$sum$27;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$27$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
									}
							}
						);
						double reduceVar$sum$26 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							double k = reduceVar$sum$26;
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								reduceVar$sum$26 = (k + l);
						}
						double reduceVar$sum$26$1 = reduceVar$sum$26;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$26$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				if(!fixedFlag$sample21)
					sample21(var20);
			}
			if(!fixedFlag$sample28)
				sample28();
			if(!fixedFlag$sample34)
				sample34();
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample47)
								sample47(var46, threadID$var46, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample47)
								sample47(var46, threadID$var46, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample28)
				sample28();
			for(int var20 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var20 >= ((0 - 1) + 1); var20 -= 1) {
				if(!fixedFlag$sample21)
					sample21(var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				logProbability$sample21[((var20 - 0) / 1)] = 0.0;
		}
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$b = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$sigma = 0.0;
		logProbability$var35 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				logProbability$sample47[((var46 - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var101[((i - 0) / 1)] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample103[((i - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
									}
							}
						);
						double reduceVar$sum$28 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							double k = reduceVar$sum$28;
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								reduceVar$sum$28 = (k + l);
						}
						double reduceVar$sum$28$1 = reduceVar$sum$28;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$28$1);
									}
							}
						);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if((fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
									}
							}
						);
						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							double k = reduceVar$sum$29;
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							if((fixedFlag$sample21 && fixedFlag$sample47))
								reduceVar$sum$29 = (k + l);
						}
						double reduceVar$sum$29$1 = reduceVar$sum$29;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if((fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$29$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}