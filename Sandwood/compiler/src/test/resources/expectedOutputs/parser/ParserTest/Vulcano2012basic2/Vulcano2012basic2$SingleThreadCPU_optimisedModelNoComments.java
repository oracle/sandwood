package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basic2$CoreInterface {
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample149 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[] guard$sample26multinomial148$global;
	private boolean[][] guard$sample26put123$global;
	private boolean[][] guard$sample26put146$global;
	private boolean[] guard$sample26put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$sales_sum;
	private double[] logProbability$sample149;
	private double[] logProbability$sample26;
	private double[] logProbability$sample82;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var145;
	private double[] logProbability$var25;
	private double[] logProbability$var80;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_ut;
	private int noProducts;
	private double r;
	private int[] sales_sum;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private double[][] weekly_ut;

	public Vulcano2012basic2$SingleThreadCPU(ExecutionTarget target) {
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
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	@Override
	public final void set$ObsSales(int[][] cv$value) {
		ObsSales = cv$value;
	}

	@Override
	public final int[][] get$Sales() {
		return Sales;
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
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final double[] get$expedNorm() {
		return expedNorm;
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample149 = (cv$value && fixedProbFlag$sample149);
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
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$expedNorm() {
		return logProbability$expedNorm;
	}

	@Override
	public final double get$logProbability$sales_sum() {
		return logProbability$sales_sum;
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
	public final double get$r() {
		return r;
	}

	@Override
	public final void set$r(double cv$value) {
		r = cv$value;
	}

	@Override
	public final int[] get$sales_sum() {
		return sales_sum;
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
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample149 = false;
	}

	private final void logProbabilityValue$sample149() {
		if(!fixedProbFlag$sample149) {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var145[t$var105] = cv$distributionAccumulator;
				logProbability$sample149[t$var105] = cv$distributionAccumulator;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample149 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$rvAccumulator = logProbability$sample149[t$var105];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var145[t$var105] = cv$rvAccumulator;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[j$var20] / 1.4142135623730951)) - 0.34657359027997264);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var25[(j$var20 - 1)] = cv$distributionAccumulator;
				logProbability$sample26[(j$var20 - 1)] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$sampleValue = logProbability$sample26[(j$var20 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var25[(j$var20 - 1)] = cv$sampleValue;
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(sales_sum[t$var78], 0.5);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var80[t$var78] = cv$distributionAccumulator;
				logProbability$sample82[t$var78] = cv$distributionAccumulator;
				logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = true;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$sampleValue = logProbability$sample82[t$var78];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var80[t$var78] = cv$sampleValue;
				logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int j$var20) {
		double cv$originalValue = ut[j$var20];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample26multinomial148$global[t$var105] = false;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26multinomial148$global[t$var105]) {
					guard$sample26multinomial148$global[t$var105] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26multinomial148$global[t$var105]) {
					guard$sample26multinomial148$global[t$var105] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26multinomial148$global[t$var105]) {
					guard$sample26multinomial148$global[t$var105] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26multinomial148$global[t$var105])
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[j$var20] = cv$proposedValue;
		exped[j$var20] = Math.exp(ut[j$var20]);
		double reduceVar$sum$0 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction46Index]);
		sum = reduceVar$sum$0;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
			guard$sample26put68$global[j$var63] = false;
		guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!guard$sample26put68$global[j$var63]) {
				guard$sample26put68$global[j$var63] = true;
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$0));
			}
		}
		if(!guard$sample26put68$global[j$var20]) {
			guard$sample26put68$global[j$var20] = true;
			expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$0));
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample26put123$global[t$var105][j$var63] = false;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			guard$sample26put123$global[t$var105][j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26put123$global[t$var105][j$var63]) {
					guard$sample26put123$global[t$var105][j$var63] = true;
					weekly_ut[t$var105][j$var63] = (expedNorm[j$var63] * Avail[t$var105][j$var63]);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26put123$global[t$var105][j$var20]) {
				guard$sample26put123$global[t$var105][j$var20] = true;
				weekly_ut[t$var105][j$var20] = (expedNorm[j$var20] * Avail[t$var105][j$var20]);
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample26put146$global[t$var105][j$var63] = false;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
				guard$sample26put146$global[t$var105][j$var140] = false;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			guard$sample26put146$global[t$var105][j$var20] = false;
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!guard$sample26put146$global[t$var105][j$var140]) {
					guard$sample26put146$global[t$var105][j$var140] = true;
					double reduceVar$denom$0 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
						reduceVar$denom$0 = (reduceVar$denom$0 + weekly_ut[t$var105][cv$reduction128Index]);
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$0);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26put146$global[t$var105][j$var63]) {
					guard$sample26put146$global[t$var105][j$var63] = true;
					double reduceVar$denom$1 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
						reduceVar$denom$1 = (reduceVar$denom$1 + weekly_ut[t$var105][cv$reduction128Index]);
					weekly_rates[t$var105][j$var63] = (weekly_ut[t$var105][j$var63] / reduceVar$denom$1);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!guard$sample26put146$global[t$var105][j$var140]) {
					guard$sample26put146$global[t$var105][j$var140] = true;
					double reduceVar$denom$2 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
						reduceVar$denom$2 = (reduceVar$denom$2 + weekly_ut[t$var105][cv$reduction128Index]);
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$2);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26put146$global[t$var105][j$var20]) {
				guard$sample26put146$global[t$var105][j$var20] = true;
				double reduceVar$denom$3 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$3 = (reduceVar$denom$3 + weekly_ut[t$var105][cv$reduction128Index]);
				weekly_rates[t$var105][j$var20] = (weekly_ut[t$var105][j$var20] / reduceVar$denom$3);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 1.4142135623730951)) - 0.34657359027997264);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			guard$sample26multinomial148$global[t$var105] = false;
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26multinomial148$global[t$var105]) {
				guard$sample26multinomial148$global[t$var105] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26multinomial148$global[t$var105]) {
				guard$sample26multinomial148$global[t$var105] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26multinomial148$global[t$var105]) {
				guard$sample26multinomial148$global[t$var105] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample26multinomial148$global[t$var105]) {
				guard$sample26multinomial148$global[t$var105] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[t$var105], noProducts, sales_sum[t$var105]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[j$var20] = cv$originalValue;
			exped[j$var20] = Math.exp(ut[j$var20]);
			double reduceVar$sum$3 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$3;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				guard$sample26put68$global[j$var63] = false;
			guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				if(!guard$sample26put68$global[j$var63]) {
					guard$sample26put68$global[j$var63] = true;
					expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$3));
				}
			}
			if(!guard$sample26put68$global[j$var20]) {
				guard$sample26put68$global[j$var20] = true;
				expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$3));
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
					guard$sample26put123$global[t$var105][j$var63] = false;
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample26put123$global[t$var105][j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
					if(!guard$sample26put123$global[t$var105][j$var63]) {
						guard$sample26put123$global[t$var105][j$var63] = true;
						weekly_ut[t$var105][j$var63] = (expedNorm[j$var63] * Avail[t$var105][j$var63]);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26put123$global[t$var105][j$var20]) {
					guard$sample26put123$global[t$var105][j$var20] = true;
					weekly_ut[t$var105][j$var20] = (expedNorm[j$var20] * Avail[t$var105][j$var20]);
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
					guard$sample26put146$global[t$var105][j$var63] = false;
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					guard$sample26put146$global[t$var105][j$var140] = false;
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample26put146$global[t$var105][j$var20] = false;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
					if(!guard$sample26put146$global[t$var105][j$var140]) {
						guard$sample26put146$global[t$var105][j$var140] = true;
						double reduceVar$denom$6 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
							reduceVar$denom$6 = (reduceVar$denom$6 + weekly_ut[t$var105][cv$reduction128Index]);
						weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$6);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
					if(!guard$sample26put146$global[t$var105][j$var63]) {
						guard$sample26put146$global[t$var105][j$var63] = true;
						double reduceVar$denom$7 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
							reduceVar$denom$7 = (reduceVar$denom$7 + weekly_ut[t$var105][cv$reduction128Index]);
						weekly_rates[t$var105][j$var63] = (weekly_ut[t$var105][j$var63] / reduceVar$denom$7);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
					if(!guard$sample26put146$global[t$var105][j$var140]) {
						guard$sample26put146$global[t$var105][j$var140] = true;
						double reduceVar$denom$8 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
							reduceVar$denom$8 = (reduceVar$denom$8 + weekly_ut[t$var105][cv$reduction128Index]);
						weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$8);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample26put146$global[t$var105][j$var20]) {
					guard$sample26put146$global[t$var105][j$var20] = true;
					double reduceVar$denom$9 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
						reduceVar$denom$9 = (reduceVar$denom$9 + weekly_ut[t$var105][cv$reduction128Index]);
					weekly_rates[t$var105][j$var20] = (weekly_ut[t$var105][j$var20] / reduceVar$denom$9);
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample26put68$global = new boolean[Math.max(0, noProducts)];
		int cv$max_j$var116 = 0;
		if((0 < T))
			cv$max_j$var116 = Math.max(0, noProducts);
		guard$sample26put123$global = new boolean[Math.max(0, T)][cv$max_j$var116];
		int cv$max_j$var140 = 0;
		if((0 < T))
			cv$max_j$var140 = Math.max(0, noProducts);
		guard$sample26put146$global = new boolean[Math.max(0, T)][cv$max_j$var140];
		guard$sample26multinomial148$global = new boolean[Math.max(0, T)];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample26)
			ut = new double[noProducts];
		exped = new double[noProducts];
		expedNorm = new double[noProducts];
		sales_sum = new int[T];
		Sales = new int[T][];
		for(int var93 = 0; var93 < T; var93 += 1)
			Sales[var93] = new int[noProducts];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			Sales[t$var105] = new int[noProducts];
		weekly_rates = new double[T][];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			weekly_rates[t$var105] = new double[noProducts];
		weekly_ut = new double[T][];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			weekly_ut[t$var105] = new double[noProducts];
		logProbability$var25 = new double[(noProducts - 1)];
		logProbability$sample26 = new double[(noProducts - 1)];
		logProbability$var80 = new double[T];
		logProbability$sample82 = new double[T];
		logProbability$var145 = new double[T];
		logProbability$sample149 = new double[T];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1)
				exped[j$var38] = Math.exp(ut[j$var38]);
			double reduceVar$sum$4 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$4;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$4));
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$, 0.5);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!fixedFlag$sample26) {
				for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1)
					weekly_ut[t$var105][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
				double reduceVar$denom$10 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$10);
			}
			DistributionSampling.sampleMultinomial(RNG$, weekly_rates[t$var105], noProducts, sales_sum[t$var105], Sales[t$var105]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1)
				exped[j$var38] = Math.exp(ut[j$var38]);
			double reduceVar$sum$6 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$6;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$6));
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1)
					weekly_ut[t$var105][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
				double reduceVar$denom$12 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$12 = (reduceVar$denom$12 + weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$12);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1)
				exped[j$var38] = Math.exp(ut[j$var38]);
			double reduceVar$sum$5 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$5;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$5));
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1)
					weekly_ut[t$var105][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
				double reduceVar$denom$11 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$11);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample26) {
			if(system$gibbsForward) {
				for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
					sample26(j$var20);
			} else {
				for(int j$var20 = (noProducts - 1); j$var20 >= 1; j$var20 -= 1)
					sample26(j$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
			logProbability$var25[(j$var20 - 1)] = Double.NaN;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				logProbability$sample26[(j$var20 - 1)] = Double.NaN;
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			logProbability$var80[t$var78] = Double.NaN;
		logProbability$sales_sum = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				logProbability$sample82[t$var78] = Double.NaN;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			logProbability$var145[t$var105] = Double.NaN;
		if(!fixedProbFlag$sample149) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				logProbability$sample149[t$var105] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1)
				exped[j$var38] = Math.exp(ut[j$var38]);
			double reduceVar$sum$7 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$7;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$7));
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1)
					weekly_ut[t$var105][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
				double reduceVar$denom$13 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$13);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsSales[cv$index1];
			int[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int t$var105 = (T - 1); t$var105 >= 0; t$var105 -= 1) {
			int[] weekly_sales = Sales[t$var105];
			int cv$multinomialSum148 = 0;
			for(int cv$multinomialIndex148 = 0; cv$multinomialIndex148 < weekly_sales.length; cv$multinomialIndex148 += 1)
				cv$multinomialSum148 = (weekly_sales[cv$multinomialIndex148] + cv$multinomialSum148);
			sales_sum[t$var105] = cv$multinomialSum148;
		}
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample26) {
			for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1)
				exped[j$var38] = Math.exp(ut[j$var38]);
			double reduceVar$sum$8 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$8;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$8));
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1)
					weekly_ut[t$var105][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
				double reduceVar$denom$14 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1)
					reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					weekly_rates[t$var105][j$var140] = (weekly_ut[t$var105][j$var140] / reduceVar$denom$14);
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic2(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        sales_sum[t] = poisson(0.5).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}