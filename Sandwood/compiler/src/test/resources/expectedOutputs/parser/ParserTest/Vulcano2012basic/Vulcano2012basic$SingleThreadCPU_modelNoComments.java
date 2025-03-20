package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basic$CoreInterface {
	private int[][] Avail;
	private double[][] ObsSales;
	private double[][] Sales;
	private int T;
	private int[] arrivals;
	private double denom;
	private double[] exped;
	private boolean fixedFlag$sample127 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample82 = false;
	private boolean fixedProbFlag$sample127 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[][] guard$sample22gaussian126$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[][] logProbability$sample127;
	private double[] logProbability$sample22;
	private double[] logProbability$sample82;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var10;
	private double[][] logProbability$var124;
	private double logProbability$var54;
	private double logProbability$var66;
	private double[] logProbability$var80;
	private int noProducts;
	private int s;
	private boolean setFlag$Sales = false;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public Vulcano2012basic$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[][] get$Avail() {
		return Avail;
	}

	@Override
	public final void set$Avail(int[][] cv$value) {
		Avail = cv$value;
	}

	@Override
	public final double[][] get$ObsSales() {
		return ObsSales;
	}

	@Override
	public final void set$ObsSales(double[][] cv$value) {
		ObsSales = cv$value;
	}

	@Override
	public final double[][] get$Sales() {
		return Sales;
	}

	@Override
	public final void set$Sales(double[][] cv$value) {
		Sales = cv$value;
		setFlag$Sales = true;
		fixedProbFlag$sample127 = false;
	}

	@Override
	public final int get$T() {
		return T;
	}

	@Override
	public final void set$T(int cv$value) {
		T = cv$value;
	}

	@Override
	public final int[] get$arrivals() {
		return arrivals;
	}

	@Override
	public final void set$arrivals(int[] cv$value) {
		arrivals = cv$value;
		setFlag$arrivals = true;
		fixedProbFlag$sample82 = false;
		fixedProbFlag$sample127 = false;
	}

	@Override
	public final double get$denom() {
		return denom;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample127() {
		return fixedFlag$sample127;
	}

	@Override
	public final void set$fixedFlag$sample127(boolean cv$value) {
		fixedFlag$sample127 = cv$value;
		fixedProbFlag$sample127 = (fixedFlag$sample127 && fixedProbFlag$sample127);
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		fixedProbFlag$sample127 = (fixedFlag$sample22 && fixedProbFlag$sample127);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		fixedFlag$sample67 = cv$value;
		fixedProbFlag$sample67 = (fixedFlag$sample67 && fixedProbFlag$sample67);
		fixedProbFlag$sample82 = (fixedFlag$sample67 && fixedProbFlag$sample82);
	}

	@Override
	public final boolean get$fixedFlag$sample82() {
		return fixedFlag$sample82;
	}

	@Override
	public final void set$fixedFlag$sample82(boolean cv$value) {
		fixedFlag$sample82 = cv$value;
		fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedProbFlag$sample82);
		fixedProbFlag$sample127 = (fixedFlag$sample82 && fixedProbFlag$sample127);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample67 = false;
		fixedProbFlag$sample82 = false;
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
	public final double get$logProbability$Sales() {
		return logProbability$Sales;
	}

	@Override
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	@Override
	public final double get$logProbability$denom() {
		return logProbability$denom;
	}

	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
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
	public final int get$s() {
		return s;
	}

	@Override
	public final void set$s(int cv$value) {
		s = cv$value;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample127 = false;
	}

	private final void logProbabilityValue$sample127() {
		if(!fixedProbFlag$sample127) {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = Sales[t$var105][j$var115];
						{
							{
								double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]);
								double var123 = 0.2;
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var122) / Math.sqrt(var123))) - (0.5 * Math.log(var123))));
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
					logProbability$var124[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample127[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample127 = ((fixedFlag$sample127 && fixedFlag$sample22) && fixedFlag$sample82);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample127[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var124[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var21];
					{
						{
							double var8 = 0.0;
							double var9 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var8) / Math.sqrt(var9))) - (0.5 * Math.log(var9))));
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
				logProbability$sample22[((var21 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
								{
									if(!cv$guard$denom) {
										cv$guard$denom = true;
										logProbability$denom = (logProbability$denom + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var10 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$sampleValue = logProbability$sample22[((var21 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(((0 <= j$var34) && (j$var34 < noProducts))) {
								{
									if(!cv$guard$denom) {
										cv$guard$denom = true;
										logProbability$denom = (logProbability$denom + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var10 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var65 = 0; var65 < T; var65 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var65];
					{
						{
							double var52 = 10.0;
							double var53 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var52, var53));
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
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$var66 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample67 = fixedFlag$sample67;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var66;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var54 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var78];
					{
						{
							double var79 = lambda[t$var78];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var79));
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
				logProbability$var80[((t$var78 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample82[((t$var78 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedFlag$sample67);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample82[((t$var78 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var80[((t$var78 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int var21) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = ut[var21];
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
					double var22 = cv$proposedValue;
					ut[var21] = cv$currentValue;
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								{
									exped[j$var34] = Math.exp(ut[j$var34]);
								}
							}
						}
					}
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										{
											double reduceVar$sum$0 = 0.0;
											for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
												double k = reduceVar$sum$0;
												double l = exped[cv$reduction42Index];
												reduceVar$sum$0 = (k + l);
											}
											sum = reduceVar$sum$0;
										}
									}
								}
							}
						}
					}
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										{
											denom = (sum / s);
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
				double cv$temp$0$var8;
				{
					cv$temp$0$var8 = 0.0;
				}
				double cv$temp$1$var9;
				{
					cv$temp$1$var9 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var8) / Math.sqrt(cv$temp$1$var9))) - (0.5 * Math.log(cv$temp$1$var9))));
				{
					{
						boolean[][] guard$sample22gaussian126 = guard$sample22gaussian126$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
												guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
									if((j$var34 == j$var115)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
											guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var35$6_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var35$6_1);
								if(((0 <= j$var34) && (j$var34 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$1 = 0.0;
											for(int cv$reduction320Index = 0; cv$reduction320Index < j$var34; cv$reduction320Index += 1) {
												double k = reduceVar$sum$1;
												double l = exped[cv$reduction320Index];
												reduceVar$sum$1 = (k + l);
											}
											for(int cv$reduction320Index = (j$var34 + 1); cv$reduction320Index < noProducts; cv$reduction320Index += 1) {
												double k = reduceVar$sum$1;
												double l = exped[cv$reduction320Index];
												reduceVar$sum$1 = (k + l);
											}
											double cv$reduced42 = reduceVar$sum$1;
											reduceVar$sum$1 = (traceTempVariable$k$6_3 + cv$reduced42);
											double traceTempVariable$sum$6_4 = reduceVar$sum$1;
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)]) {
														guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var122;
																			{
																				double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / traceTempVariable$denom$6_7) * arrivals[t$var105]);
																				cv$temp$2$var122 = var122;
																			}
																			double cv$temp$3$var123;
																			{
																				cv$temp$3$var123 = 0.2;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$2$var122) / Math.sqrt(cv$temp$3$var123))) - (0.5 * Math.log(cv$temp$3$var123)))));
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
						double traceTempVariable$var35$7_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var116$7_3 = Math.exp(traceTempVariable$var35$7_1);
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
									if((j$var34 == j$var115)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(!guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)]) {
												guard$sample22gaussian126[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$4$var122;
																	{
																		double var122 = (((traceTempVariable$var116$7_3 * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]);
																		cv$temp$4$var122 = var122;
																	}
																	double cv$temp$5$var123;
																	{
																		cv$temp$5$var123 = 0.2;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$4$var122) / Math.sqrt(cv$temp$5$var123))) - (0.5 * Math.log(cv$temp$5$var123)))));
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
			double var22 = cv$originalValue;
			ut[var21] = var22;
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						{
							exped[j$var34] = Math.exp(ut[j$var34]);
						}
					}
				}
			}
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						if(((0 <= j$var34) && (j$var34 < noProducts))) {
							{
								{
									double reduceVar$sum$2 = 0.0;
									for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
										double k = reduceVar$sum$2;
										double l = exped[cv$reduction42Index];
										reduceVar$sum$2 = (k + l);
									}
									sum = reduceVar$sum$2;
								}
							}
						}
					}
				}
			}
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						if(((0 <= j$var34) && (j$var34 < noProducts))) {
							{
								{
									denom = (sum / s);
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample67(int var65) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
						if((var65 == t$var78)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var78]);
												cv$count = (cv$count + 1);
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
		double var66 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var65] = var66;
	}

	private final void sample82(int t$var78) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		int cv$originalValue = arrivals[t$var78];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					int var81 = cv$proposedValue;
					arrivals[t$var78] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var79;
				{
					double var79 = lambda[t$var78];
					cv$temp$0$var79 = var79;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var79));
				{
					{
						int traceTempVariable$var121$1_1 = cv$currentValue;
						for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
							if((t$var78 == t$var105)) {
								for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$1$var122;
													{
														double var122 = (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * traceTempVariable$var121$1_1);
														cv$temp$1$var122 = var122;
													}
													double cv$temp$2$var123;
													{
														cv$temp$2$var123 = 0.2;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - cv$temp$1$var122) / Math.sqrt(cv$temp$2$var123))) - (0.5 * Math.log(cv$temp$2$var123)))));
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
			int var81 = cv$originalValue;
			arrivals[t$var78] = var81;
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_t$var105 = 0;
		int cv$max_j$var115 = 0;
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			cv$max_j$var115 = Math.max(cv$max_j$var115, ((noProducts - 0) / 1));
		cv$max_t$var105 = Math.max(cv$max_t$var105, ((T - 0) / 1));
		guard$sample22gaussian126$global = new boolean[cv$max_t$var105][cv$max_j$var115];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut) {
			{
				ut = new double[noProducts];
			}
		}
		{
			exped = new double[noProducts];
		}
		if(!setFlag$lambda) {
			{
				lambda = new double[T];
			}
		}
		if(!setFlag$arrivals) {
			{
				arrivals = new int[T];
			}
		}
		if(!setFlag$Sales) {
			{
				Sales = new double[T][];
				for(int var93 = 0; var93 < T; var93 += 1)
					Sales[var93] = new double[noProducts];
				for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
					Sales[t$var105] = new double[noProducts];
			}
		}
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var80 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample82 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var124 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				logProbability$var124[((t$var105 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample127 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				logProbability$sample127[((t$var105 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		double reduceVar$sum$3 = 0.0;
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			double k = reduceVar$sum$3;
			double l = exped[cv$reduction42Index];
			if(!fixedFlag$sample22)
				reduceVar$sum$3 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$3;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		for(int var65 = 0; var65 < T; var65 += 1) {
			if(!fixedFlag$sample67)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
			if(!fixedFlag$sample82)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			double[] weekly_sales = Sales[t$var105];
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
				if(!fixedFlag$sample127)
					weekly_sales[j$var115] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$)) + (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]));
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		double reduceVar$sum$5 = 0.0;
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			double k = reduceVar$sum$5;
			double l = exped[cv$reduction42Index];
			if(!fixedFlag$sample22)
				reduceVar$sum$5 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$5;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		for(int var65 = 0; var65 < T; var65 += 1) {
			if(!fixedFlag$sample67)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
			if(!fixedFlag$sample82)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		double reduceVar$sum$4 = 0.0;
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			double k = reduceVar$sum$4;
			double l = exped[cv$reduction42Index];
			if(!fixedFlag$sample22)
				reduceVar$sum$4 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$4;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		for(int var65 = 0; var65 < T; var65 += 1) {
			if(!fixedFlag$sample67)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
			if(!fixedFlag$sample82)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				if(!fixedFlag$sample22)
					sample22(var21);
			}
			for(int var65 = 0; var65 < T; var65 += 1) {
				if(!fixedFlag$sample67)
					sample67(var65);
			}
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				if(!fixedFlag$sample82)
					sample82(t$var78);
			}
		} else {
			for(int t$var78 = (T - ((((T - 1) - 0) % 1) + 1)); t$var78 >= ((0 - 1) + 1); t$var78 -= 1) {
				if(!fixedFlag$sample82)
					sample82(t$var78);
			}
			for(int var65 = (T - ((((T - 1) - 0) % 1) + 1)); var65 >= ((0 - 1) + 1); var65 -= 1) {
				if(!fixedFlag$sample67)
					sample67(var65);
			}
			for(int var21 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var21 >= ((0 - 1) + 1); var21 -= 1) {
				if(!fixedFlag$sample22)
					sample22(var21);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$denom = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[((var21 - 0) / 1)] = 0.0;
		}
		logProbability$var54 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample67)
			logProbability$var66 = 0.0;
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			logProbability$var80[((t$var78 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				logProbability$sample82[((t$var78 - 0) / 1)] = 0.0;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				logProbability$var124[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample127) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
					logProbability$sample127[((t$var105 - 0) / 1)][((j$var115 - 0) / 1)] = 0.0;
			}
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
		if(fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(fixedFlag$sample82)
			logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		double reduceVar$sum$6 = 0.0;
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
			double k = reduceVar$sum$6;
			double l = exped[cv$reduction42Index];
			if(!fixedFlag$sample22)
				reduceVar$sum$6 = (k + l);
		}
		if(!fixedFlag$sample22)
			sum = reduceVar$sum$6;
		if(!fixedFlag$sample22)
			denom = (sum / s);
		for(int var65 = 0; var65 < T; var65 += 1) {
			if(!fixedFlag$sample67)
				lambda[var65] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
			if(!fixedFlag$sample82)
				arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$, lambda[t$var78]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		double[][] cv$source1 = ObsSales;
		double[][] cv$target1 = Sales;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[] cv$source2 = cv$source1[cv$index1];
			double[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(setFlag$ut)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		if(setFlag$ut) {
			double reduceVar$sum$7 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1) {
				double k = reduceVar$sum$7;
				double l = exped[cv$reduction42Index];
				reduceVar$sum$7 = (k + l);
			}
			sum = reduceVar$sum$7;
		}
		if(setFlag$ut)
			denom = (sum / s);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n"
		     + "    // Avail is the availability matrix, T-by-noProducts\n"
		     + "    // s is the normalization constant (market share), number between 0 and 1\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "    exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n"
		     + "\n"
		     + "    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n"
		     + "    double[ ] lambda = gamma(10,10).sample(T);\n"
		     + "\n"
		     + "    // draw arrivals\n"
		     + "    int[] arrivals = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "    arrivals[t]= poisson(lambda[t]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    double[][] Sales = new double[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities\n"
		     + "        // (does it matter if choice probabilities or individual choices?)\n"
		     + "        // let's try aggregate first\n"
		     + "\n"
		     + "        double[] weekly_sales = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n"
		     + "        }\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}