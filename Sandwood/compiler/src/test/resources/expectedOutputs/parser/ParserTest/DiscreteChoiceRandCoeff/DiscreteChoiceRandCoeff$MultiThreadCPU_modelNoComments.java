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
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean[] guard$sample22categorical75$global;
	private boolean[][] guard$sample22put74$global;
	private boolean[][] guard$sample41categorical75$global;
	private boolean[][][] guard$sample41put74$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample22;
	private double[] logProbability$sample41;
	private double[] logProbability$sample76;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double logProbability$var15;
	private double logProbability$var26;
	private double logProbability$var32;
	private double logProbability$var34;
	private double[] logProbability$var72;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private boolean setFlag$beta = false;
	private boolean setFlag$choices = false;
	private boolean setFlag$prob = false;
	private boolean setFlag$ut = false;
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
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value) {
		beta = cv$value;
		setFlag$beta = true;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final void set$choices(int[] cv$value) {
		choices = cv$value;
		setFlag$choices = true;
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		fixedProbFlag$sample76 = (fixedFlag$sample22 && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample41 = (fixedFlag$sample29 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample41 = (fixedFlag$sample35 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
		fixedProbFlag$sample76 = (fixedFlag$sample41 && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (fixedFlag$sample76 && fixedProbFlag$sample76);
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
	public final void set$prob(double[][] cv$value) {
		prob = cv$value;
		setFlag$prob = true;
	}

	@Override
	public final double get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double cv$value) {
		sigma = cv$value;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var19];
					{
						{
							double var13 = 0.0;
							double var14 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var13, var14));
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
				logProbability$sample22[((var19 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
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
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
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
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
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
			logProbability$var15 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				double cv$sampleValue = logProbability$sample22[((var19 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
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
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
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
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
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
			logProbability$var15 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = b;
				{
					{
						double var24 = 0.0;
						double var25 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var24, var25));
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
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$b = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = sigma;
				{
					{
						double var30 = 2.0;
						double var31 = 2.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var30, var31));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$sigma = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = beta[var38];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, b, sigma));
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
				logProbability$sample41[((var38 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
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
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
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
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$beta = (logProbability$beta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = ((fixedFlag$sample41 && fixedFlag$sample29) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				double cv$sampleValue = logProbability$sample41[((var38 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$prob = false;
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
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
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
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
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
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
			logProbability$var34 = cv$rvAccumulator;
			logProbability$beta = (logProbability$beta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = choices[i];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, prob[((i - 0) / 1)]));
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
				logProbability$var72[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample76[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample22) && fixedFlag$sample41);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample76[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int var19) {
		double cv$originalValue = ut[var19];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var20 = cv$proposedValue;
					ut[var19] = cv$currentValue;
					{
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1)
									exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
							}
						}
					}
					{
						boolean[][] guard$sample22put74 = guard$sample22put74$global;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
												guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1)
											guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
												if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
													guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
													{
														double reduceVar$sum$15 = 0.0;
														for(int cv$reduction911Index = 0; cv$reduction911Index < noProducts; cv$reduction911Index += 1) {
															double k = reduceVar$sum$15;
															double l = exped[((i - 0) / 1)][cv$reduction911Index];
															reduceVar$sum$15 = (k + l);
														}
														prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$15);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1) {
											if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
												guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
												{
													double reduceVar$sum$16 = 0.0;
													for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
														double k = reduceVar$sum$16;
														double l = exped[((i - 0) / 1)][cv$reduction63Index];
														reduceVar$sum$16 = (k + l);
													}
													prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$16);
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
				double cv$temp$0$var13;
				{
					cv$temp$0$var13 = 0.0;
				}
				double cv$temp$1$var14;
				{
					cv$temp$1$var14 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$currentValue, cv$temp$0$var13, cv$temp$1$var14));
				{
					{
						boolean[] guard$sample22categorical75 = guard$sample22categorical75$global;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											guard$sample22categorical75[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1)
											guard$sample22categorical75[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var49$8_1 = cv$currentValue;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$k$8_4 = Math.exp((traceTempVariable$var49$8_1 - (beta[i] * Prices[i][j$var48])));
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$17 = 0.0;
												for(int cv$reduction985Index = 0; cv$reduction985Index < j$var48; cv$reduction985Index += 1) {
													double k = reduceVar$sum$17;
													double l = exped[((i - 0) / 1)][cv$reduction985Index];
													reduceVar$sum$17 = (k + l);
												}
												for(int cv$reduction985Index = (j$var48 + 1); cv$reduction985Index < noProducts; cv$reduction985Index += 1) {
													double k = reduceVar$sum$17;
													double l = exped[((i - 0) / 1)][cv$reduction985Index];
													reduceVar$sum$17 = (k + l);
												}
												double cv$reduced63 = reduceVar$sum$17;
												reduceVar$sum$17 = (traceTempVariable$k$8_4 + cv$reduced63);
												double traceTempVariable$sum$8_5 = reduceVar$sum$17;
												if(!guard$sample22categorical75[((i - 0) / 1)]) {
													guard$sample22categorical75[((i - 0) / 1)] = true;
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
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)));
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
						double traceTempVariable$var49$9_1 = cv$currentValue;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$var69$9_4 = Math.exp((traceTempVariable$var49$9_1 - (beta[i] * Prices[i][j$var48])));
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample22categorical75[((i - 0) / 1)]) {
												guard$sample22categorical75[((i - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob[((i - 0) / 1)];
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)));
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN(cv$ratio))) {
			double var20 = cv$originalValue;
			ut[var19] = var20;
			{
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1)
							exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
					}
				}
			}
			{
				boolean[][] guard$sample22put74 = guard$sample22put74$global;
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
										guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var48 == j$var68)) {
								for(int i = 0; i < noObs; i += 1)
									guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
											guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
											{
												double reduceVar$sum$18 = 0.0;
												for(int cv$reduction1069Index = 0; cv$reduction1069Index < noProducts; cv$reduction1069Index += 1) {
													double k = reduceVar$sum$18;
													double l = exped[((i - 0) / 1)][cv$reduction1069Index];
													reduceVar$sum$18 = (k + l);
												}
												prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$18);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var48 == j$var68)) {
								for(int i = 0; i < noObs; i += 1) {
									if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
										guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
										{
											double reduceVar$sum$19 = 0.0;
											for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
												double k = reduceVar$sum$19;
												double l = exped[((i - 0) / 1)][cv$reduction63Index];
												reduceVar$sum$19 = (k + l);
											}
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$19);
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

	private final void sample29() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					{
						for(int var38 = 0; var38 < noObs; var38 += 1) {
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							cv$sum = (cv$sum + (cv$denominator * (beta[var38] - cv$numerator)));
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

	private final void sample35() {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					{
						for(int var38 = 0; var38 < noObs; var38 += 1) {
							double cv$var34$mu = b;
							double cv$var34$diff = (cv$var34$mu - beta[var38]);
							cv$sum = (cv$sum + (cv$var34$diff * cv$var34$diff));
							cv$count = (cv$count + 1);
						}
					}
				}
			}
		}
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void sample41(int var38, int threadID$cv$var38, Rng RNG$) {
		double cv$originalValue = beta[var38];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var39 = cv$proposedValue;
					beta[var38] = cv$currentValue;
					{
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
									exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
							}
						}
					}
					{
						boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
												guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68))
											guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
												if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
													guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
													{
														double reduceVar$sum$20 = 0.0;
														for(int cv$reduction1178Index = 0; cv$reduction1178Index < noProducts; cv$reduction1178Index += 1) {
															double k = reduceVar$sum$20;
															double l = exped[((i - 0) / 1)][cv$reduction1178Index];
															reduceVar$sum$20 = (k + l);
														}
														prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$20);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
												guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
												{
													double reduceVar$sum$21 = 0.0;
													for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
														double k = reduceVar$sum$21;
														double l = exped[((i - 0) / 1)][cv$reduction63Index];
														reduceVar$sum$21 = (k + l);
													}
													prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$21);
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$currentValue, cv$temp$0$b, cv$temp$1$sigma));
				{
					{
						boolean[] guard$sample41categorical75 = guard$sample41categorical75$global[threadID$cv$var38];
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											guard$sample41categorical75[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68))
											guard$sample41categorical75[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var50$8_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									double traceTempVariable$k$8_4 = Math.exp((ut[j$var48] - (traceTempVariable$var50$8_1 * Prices[i][j$var48])));
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$22 = 0.0;
												for(int cv$reduction1252Index = 0; cv$reduction1252Index < j$var48; cv$reduction1252Index += 1) {
													double k = reduceVar$sum$22;
													double l = exped[((i - 0) / 1)][cv$reduction1252Index];
													reduceVar$sum$22 = (k + l);
												}
												for(int cv$reduction1252Index = (j$var48 + 1); cv$reduction1252Index < noProducts; cv$reduction1252Index += 1) {
													double k = reduceVar$sum$22;
													double l = exped[((i - 0) / 1)][cv$reduction1252Index];
													reduceVar$sum$22 = (k + l);
												}
												double cv$reduced63 = reduceVar$sum$22;
												reduceVar$sum$22 = (traceTempVariable$k$8_4 + cv$reduced63);
												double traceTempVariable$sum$8_5 = reduceVar$sum$22;
												if(!guard$sample41categorical75[((i - 0) / 1)]) {
													guard$sample41categorical75[((i - 0) / 1)] = true;
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
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$2$prob)));
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
						double traceTempVariable$var50$9_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									double traceTempVariable$var69$9_4 = Math.exp((ut[j$var48] - (traceTempVariable$var50$9_1 * Prices[i][j$var48])));
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample41categorical75[((i - 0) / 1)]) {
												guard$sample41categorical75[((i - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob[((i - 0) / 1)];
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(choices[i], cv$temp$3$prob)));
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN(cv$ratio))) {
			double var39 = cv$originalValue;
			beta[var38] = var39;
			{
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
							exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
					}
				}
			}
			{
				boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
										guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68))
									guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
											guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
											{
												double reduceVar$sum$23 = 0.0;
												for(int cv$reduction1336Index = 0; cv$reduction1336Index < noProducts; cv$reduction1336Index += 1) {
													double k = reduceVar$sum$23;
													double l = exped[((i - 0) / 1)][cv$reduction1336Index];
													reduceVar$sum$23 = (k + l);
												}
												prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$23);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
									if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
										guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
										{
											double reduceVar$sum$24 = 0.0;
											for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
												double k = reduceVar$sum$24;
												double l = exped[((i - 0) / 1)][cv$reduction63Index];
												reduceVar$sum$24 = (k + l);
											}
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$24);
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
			int cv$max_j$var68 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var68 = Math.max(cv$max_j$var68, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			guard$sample22put74$global = new boolean[cv$max_i][cv$max_j$var68];
		}
		{
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			guard$sample22categorical75$global = new boolean[cv$max_i];
		}
		{
			int cv$max_i = 0;
			int cv$max_j$var68 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var68 = Math.max(cv$max_j$var68, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample41put74$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample41put74$global[cv$index] = new boolean[cv$max_i][cv$max_j$var68];
			}
		}
		{
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample41categorical75$global = new boolean[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample41categorical75$global[cv$index] = new boolean[cv$max_i];
			}
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut) {
			{
				ut = new double[noProducts];
			}
		}
		if(!setFlag$beta) {
			{
				beta = new double[noObs];
			}
		}
		if(!setFlag$choices) {
			{
				choices = new int[noObs];
			}
		}
		{
			exped = new double[((((noObs - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < noObs; i += 1)
				exped[((i - 0) / 1)] = new double[noProducts];
		}
		if(!setFlag$prob) {
			{
				prob = new double[((((noObs - 1) - 0) / 1) + 1)][];
				for(int i = 0; i < noObs; i += 1)
					prob[((i - 0) / 1)] = new double[noProducts];
			}
		}
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample41 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var72 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample76 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = DistributionSampling.sampleGaussian(RNG$1, b, sigma);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						double reduceVar$sum$25 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							double k = reduceVar$sum$25;
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								reduceVar$sum$25 = (k + l);
						}
						double reduceVar$sum$25$1 = reduceVar$sum$25;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$25$1);
									}
							}
						);
						if(!fixedFlag$sample76)
							choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[((i - 0) / 1)]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = DistributionSampling.sampleGaussian(RNG$1, b, sigma);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						double reduceVar$sum$27 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							double k = reduceVar$sum$27;
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								reduceVar$sum$27 = (k + l);
						}
						double reduceVar$sum$27$1 = reduceVar$sum$27;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$27$1);
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
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = DistributionSampling.sampleGaussian(RNG$1, b, sigma);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						double reduceVar$sum$26 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							double k = reduceVar$sum$26;
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								reduceVar$sum$26 = (k + l);
						}
						double reduceVar$sum$26$1 = reduceVar$sum$26;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$26$1);
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
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				if(!fixedFlag$sample22)
					sample22(var19);
			}
			if(!fixedFlag$sample29)
				sample29();
			if(!fixedFlag$sample35)
				sample35();
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
							if(!fixedFlag$sample41)
								sample41(var38, threadID$var38, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
							if(!fixedFlag$sample41)
								sample41(var38, threadID$var38, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample29)
				sample29();
			for(int var19 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var19 >= ((0 - 1) + 1); var19 -= 1) {
				if(!fixedFlag$sample22)
					sample22(var19);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var15 = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var19 = 0; var19 < noProducts; var19 += 1)
				logProbability$sample22[((var19 - 0) / 1)] = 0.0;
		}
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$b = 0.0;
		logProbability$var32 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sigma = 0.0;
		logProbability$var34 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int var38 = 0; var38 < noObs; var38 += 1)
				logProbability$sample41[((var38 - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var72[((i - 0) / 1)] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample76) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample76[((i - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = DistributionSampling.sampleGaussian(RNG$1, b, sigma);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						double reduceVar$sum$28 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							double k = reduceVar$sum$28;
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								reduceVar$sum$28 = (k + l);
						}
						double reduceVar$sum$28$1 = reduceVar$sum$28;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$28$1);
									}
							}
						);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if((setFlag$ut && setFlag$beta))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							double k = reduceVar$sum$29;
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							reduceVar$sum$29 = (k + l);
						}
						double reduceVar$sum$29$1 = reduceVar$sum$29;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if((setFlag$ut && setFlag$beta))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$29$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n    //can we set the first element to 0? like ut[0] <~ 0\n\n    //priors for distribution of beta\n    double b = gaussian(0,10).sample();\n    double sigma =  inverseGamma(2,2).sample();\n\n    double[] beta = gaussian(b, sigma).sample(noObs);\n\n    int[] choices = new int[noObs];\n\n    for (int i:[0..noObs)){\n        // calculate choice probabilities for consumer i\n\n        double[] exped = new double[noProducts];\n        for(int j : [0..noProducts)) {\n            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n            }\n        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n        public double[] prob = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            prob[j] = exped[j] / sum;\n        }\n        // emit choices of consumer i\n        choices[i] = categorical(prob).sample();\n                                }\n\n    // assert that generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}