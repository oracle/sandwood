package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basicDG$CoreInterface {
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample125 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample125 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] guard$sample34multinomial124$global;
	private boolean[][] guard$sample34put102$global;
	private boolean[][] guard$sample34put123$global;
	private boolean[] guard$sample34put61$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample125;
	private double[] logProbability$sample34;
	private double[] logProbability$sample88;
	private double[] logProbability$sample90;
	private double logProbability$ut;
	private double[] logProbability$var111;
	private double logProbability$var23;
	private double[] logProbability$var77;
	private double[] logProbability$var79;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private int[][] sales;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012basicDG$SingleThreadCPU(ExecutionTarget target) {
		super(target);
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
	public final int[] get$arrivals() {
		return arrivals;
	}

	@Override
	public final void set$arrivals(int[] cv$value) {
		arrivals = cv$value;
		setFlag$arrivals = true;
	}

	@Override
	public final boolean[][] get$avail() {
		return avail;
	}

	@Override
	public final void set$avail(boolean[][] cv$value) {
		avail = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample125() {
		return fixedFlag$sample125;
	}

	@Override
	public final void set$fixedFlag$sample125(boolean cv$value) {
		fixedFlag$sample125 = cv$value;
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
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
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
	}

	@Override
	public final int get$numTimeSteps() {
		return numTimeSteps;
	}

	@Override
	public final double get$r() {
		return 0.3;
	}

	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	@Override
	public final void set$weekly_sales(int[][] cv$value) {
		weekly_sales = cv$value;
		setFlag$weekly_sales = true;
	}

	private final void logProbabilityValue$sample125() {
		if(!fixedProbFlag$sample125) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var111[t] = cv$distributionAccumulator;
					logProbability$sample125[t] = cv$distributionAccumulator;
					if((0 < avail[0].length))
						logProbability$sales = (logProbability$sales + cv$distributionAccumulator);
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample125 = ((fixedFlag$sample125 && fixedFlag$sample34) && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample125[t];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var111[t] = cv$sampleValue;
					if((0 < avail[0].length))
						logProbability$sales = (logProbability$sales + cv$sampleValue);
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$sampleAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(ut[var27]);
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					logProbability$sample34[var27] = cv$distributionAccumulator;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					if((0 < avail[0].length)) {
						cv$guard$expedNorm = true;
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					}
					if(!cv$guard$expedNorm)
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_ut)) {
								cv$guard$weekly_ut = true;
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_ut && avail[t][var27])) {
							cv$guard$weekly_ut = true;
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
				}
				cv$accumulator = cv$sampleAccumulator;
				logProbability$var23 = cv$sampleAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$sampleValue = logProbability$sample34[var27];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					if((0 < avail[0].length)) {
						cv$guard$expedNorm = true;
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					}
					if(!cv$guard$expedNorm)
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_ut)) {
								cv$guard$weekly_ut = true;
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_ut && avail[t][var27])) {
							cv$guard$weekly_ut = true;
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
				}
				cv$accumulator = cv$rvAccumulator;
				logProbability$var23 = cv$rvAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(lambda[t], 10.0, 10.0);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var77[t] = cv$distributionAccumulator;
					logProbability$sample88[t] = cv$distributionAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample88[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var77[t] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					int reduceVar$numSales$4 = 0;
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						reduceVar$numSales$4 = (reduceVar$numSales$4 + ObsSales[t][cv$reduction78Index]);
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson((arrivals[t] - reduceVar$numSales$4), lambda[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var79[t] = cv$distributionAccumulator;
					logProbability$sample90[t] = cv$distributionAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample90[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var79[t] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample34(int var27) {
		double cv$originalValue = ut[var27];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34multinomial124$global[t] = false;
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34multinomial124$global[t] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34multinomial124$global[t] = false;
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34multinomial124$global[t] = false;
			}
			if(((0 < exped.length) && (0 < avail[0].length))) {
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial124$global[t])) {
							guard$sample34multinomial124$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((avail[t][j$var50] && !guard$sample34multinomial124$global[t])) {
							guard$sample34multinomial124$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((((0 < weekly_ut[t].length) && !guard$sample34multinomial124$global[t]) && avail[t][var27])) {
					guard$sample34multinomial124$global[t] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample34multinomial124$global[t] && avail[t][var27])) {
					guard$sample34multinomial124$global[t] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var27] = cv$proposedValue;
		exped[var27] = Math.exp(ut[var27]);
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
			guard$sample34put61$global[j$var50] = false;
		guard$sample34put61$global[var27] = false;
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			if(!guard$sample34put61$global[j$var50]) {
				guard$sample34put61$global[j$var50] = true;
				double reduceVar$sum$0 = 0.0;
				for(int cv$reduction277Index = 0; cv$reduction277Index < avail[0].length; cv$reduction277Index += 1)
					reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction277Index]);
				expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$0 * 0.3));
			}
		}
		if(!guard$sample34put61$global[var27]) {
			guard$sample34put61$global[var27] = true;
			double reduceVar$sum$1 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction50Index]);
			expedNorm[var27] = (exped[var27] / (reduceVar$sum$1 * 0.3));
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34put102$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34put102$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var50] && !guard$sample34put102$global[t][j$var50])) {
					guard$sample34put102$global[t][j$var50] = true;
					weekly_ut[t][j$var50] = expedNorm[j$var50];
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample34put102$global[t][var27] && avail[t][var27])) {
				guard$sample34put102$global[t][var27] = true;
				weekly_ut[t][var27] = expedNorm[var27];
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						guard$sample34put123$global[t][j$var107] = false;
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34put123$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
					guard$sample34put123$global[t][j$var107] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34put123$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						if(!guard$sample34put123$global[t][j$var107]) {
							guard$sample34put123$global[t][j$var107] = true;
							double reduceVar$denom$0 = 0.0;
							for(int cv$reduction716Index = 0; cv$reduction716Index <= avail[0].length; cv$reduction716Index += 1)
								reduceVar$denom$0 = (reduceVar$denom$0 + weekly_ut[t][cv$reduction716Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$0);
						}
					}
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var50] && !guard$sample34put123$global[t][j$var50])) {
					guard$sample34put123$global[t][j$var50] = true;
					double reduceVar$denom$1 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$1 = (reduceVar$denom$1 + weekly_ut[t][cv$reduction111Index]);
					weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$1);
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
					if(!guard$sample34put123$global[t][j$var107]) {
						guard$sample34put123$global[t][j$var107] = true;
						double reduceVar$denom$2 = 0.0;
						for(int cv$reduction816Index = 0; cv$reduction816Index <= avail[0].length; cv$reduction816Index += 1)
							reduceVar$denom$2 = (reduceVar$denom$2 + weekly_ut[t][cv$reduction816Index]);
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$2);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((avail[t][var27] && !guard$sample34put123$global[t][var27])) {
				guard$sample34put123$global[t][var27] = true;
				double reduceVar$denom$3 = 0.0;
				for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
					reduceVar$denom$3 = (reduceVar$denom$3 + weekly_ut[t][cv$reduction111Index]);
				weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$3);
			}
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34multinomial124$global[t] = false;
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34multinomial124$global[t] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34multinomial124$global[t] = false;
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34multinomial124$global[t] = false;
		}
		if(((0 < exped.length) && (0 < avail[0].length))) {
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial124$global[t])) {
						guard$sample34multinomial124$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34multinomial124$global[t])) {
						guard$sample34multinomial124$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((((0 < weekly_ut[t].length) && !guard$sample34multinomial124$global[t]) && avail[t][var27])) {
				guard$sample34multinomial124$global[t] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample34multinomial124$global[t] && avail[t][var27])) {
				guard$sample34multinomial124$global[t] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var27] = cv$originalValue;
			exped[var27] = Math.exp(ut[var27]);
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
				guard$sample34put61$global[j$var50] = false;
			guard$sample34put61$global[var27] = false;
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!guard$sample34put61$global[j$var50]) {
					guard$sample34put61$global[j$var50] = true;
					double reduceVar$sum$6 = 0.0;
					for(int cv$reduction1462Index = 0; cv$reduction1462Index < avail[0].length; cv$reduction1462Index += 1)
						reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction1462Index]);
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$6 * 0.3));
				}
			}
			if(!guard$sample34put61$global[var27]) {
				guard$sample34put61$global[var27] = true;
				double reduceVar$sum$7 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction50Index]);
				expedNorm[var27] = (exped[var27] / (reduceVar$sum$7 * 0.3));
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34put102$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34put102$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34put102$global[t][j$var50])) {
						guard$sample34put102$global[t][j$var50] = true;
						weekly_ut[t][j$var50] = expedNorm[j$var50];
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample34put102$global[t][var27] && avail[t][var27])) {
					guard$sample34put102$global[t][var27] = true;
					weekly_ut[t][var27] = expedNorm[var27];
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
							guard$sample34put123$global[t][j$var107] = false;
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34put123$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						guard$sample34put123$global[t][j$var107] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34put123$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
							if(!guard$sample34put123$global[t][j$var107]) {
								guard$sample34put123$global[t][j$var107] = true;
								double reduceVar$denom$6 = 0.0;
								for(int cv$reduction1901Index = 0; cv$reduction1901Index <= avail[0].length; cv$reduction1901Index += 1)
									reduceVar$denom$6 = (reduceVar$denom$6 + weekly_ut[t][cv$reduction1901Index]);
								weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$6);
							}
						}
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34put123$global[t][j$var50])) {
						guard$sample34put123$global[t][j$var50] = true;
						double reduceVar$denom$7 = 0.0;
						for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
							reduceVar$denom$7 = (reduceVar$denom$7 + weekly_ut[t][cv$reduction111Index]);
						weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$7);
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						if(!guard$sample34put123$global[t][j$var107]) {
							guard$sample34put123$global[t][j$var107] = true;
							double reduceVar$denom$8 = 0.0;
							for(int cv$reduction2001Index = 0; cv$reduction2001Index <= avail[0].length; cv$reduction2001Index += 1)
								reduceVar$denom$8 = (reduceVar$denom$8 + weekly_ut[t][cv$reduction2001Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$8);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][var27] && !guard$sample34put123$global[t][var27])) {
					guard$sample34put123$global[t][var27] = true;
					double reduceVar$denom$9 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$9 = (reduceVar$denom$9 + weekly_ut[t][cv$reduction111Index]);
					weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$9);
				}
			}
		}
	}

	private final void sample88(int t) {
		int reduceVar$numSales$0 = 0;
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			reduceVar$numSales$0 = (reduceVar$numSales$0 + ObsSales[t][cv$reduction78Index]);
		lambda[t] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, (arrivals[t] - reduceVar$numSales$0), 1);
	}

	private final void sample90(int t) {
		int reduceVar$numSales$1 = 0;
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			reduceVar$numSales$1 = (reduceVar$numSales$1 + ObsSales[t][cv$reduction78Index]);
		int cv$originalValue = (arrivals[t] - reduceVar$numSales$1);
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t]));
		int reduceVar$numSales$2 = 0;
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			reduceVar$numSales$2 = (reduceVar$numSales$2 + ObsSales[t][cv$reduction78Index]);
		arrivals[t] = (reduceVar$numSales$2 + cv$proposedValue);
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			int reduceVar$numSales$3 = 0;
			for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
				reduceVar$numSales$3 = (reduceVar$numSales$3 + ObsSales[t][cv$reduction78Index]);
			arrivals[t] = (reduceVar$numSales$3 + cv$originalValue);
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var50 = 0;
		if((0 < avail.length))
			cv$max_j$var50 = avail[0].length;
		guard$sample34put61$global = new boolean[cv$max_j$var50];
		{
			int cv$max_t = 0;
			int cv$max_j$var86 = 0;
			if((0 < avail.length)) {
				cv$max_j$var86 = avail[0].length;
				cv$max_t = avail.length;
			}
			guard$sample34put102$global = new boolean[cv$max_t][cv$max_j$var86];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var107 = 0;
			if((0 < avail.length)) {
				cv$max_j$var107 = (avail[0].length + 1);
				cv$max_t = avail.length;
			}
			guard$sample34put123$global = new boolean[cv$max_t][cv$max_j$var107];
		}
		int cv$max_t = 0;
		if((0 < avail.length))
			cv$max_t = avail.length;
		guard$sample34multinomial124$global = new boolean[cv$max_t];
	}

	@Override
	public final void allocator() {
		if((0 < numTimeSteps)) {
			ut = new double[avail[0].length];
			exped = new double[avail[0].length];
			expedNorm = new double[avail[0].length];
			sales = new int[avail.length][];
			for(int var58 = 0; var58 < avail.length; var58 += 1)
				sales[var58] = new int[avail[0].length];
		}
		if(!setFlag$lambda)
			lambda = new double[avail.length];
		if(!setFlag$arrivals)
			arrivals = new int[avail.length];
		if((0 < avail.length)) {
			for(int t = 0; t < avail.length; t += 1)
				weekly_rates[t] = new double[(avail[0].length + 1)];
		}
		weekly_rates = new double[avail.length][];
		if((0 < avail.length)) {
			for(int t = 0; t < avail.length; t += 1)
				weekly_ut[t] = new double[(avail[0].length + 1)];
		}
		weekly_ut = new double[avail.length][];
		if(!setFlag$weekly_sales) {
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_sales[t] = new int[(avail[0].length + 1)];
			}
			weekly_sales = new int[avail.length][];
		}
		logProbability$sample34 = new double[avail[0].length];
		logProbability$var77 = new double[avail.length];
		logProbability$sample88 = new double[avail.length];
		logProbability$var79 = new double[avail.length];
		logProbability$sample90 = new double[avail.length];
		logProbability$var111 = new double[avail.length];
		logProbability$sample125 = new double[avail.length];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				double reduceVar$sum$8 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$8 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				if(!fixedFlag$sample90) {
					int reduceVar$numSales$5 = 0;
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						reduceVar$numSales$5 = (reduceVar$numSales$5 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$5 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				if(!fixedFlag$sample34) {
					double reduceVar$denom$10 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$10);
				}
				if(!fixedFlag$sample125) {
					DistributionSampling.sampleMultinomial(RNG$, weekly_rates[t], arrivals[t], weekly_sales[t]);
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				double reduceVar$sum$10 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$10 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				if(!fixedFlag$sample90) {
					int reduceVar$numSales$7 = 0;
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						reduceVar$numSales$7 = (reduceVar$numSales$7 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$7 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				if(!fixedFlag$sample34) {
					double reduceVar$denom$12 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$12 = (reduceVar$denom$12 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$12);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				double reduceVar$sum$9 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$9 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				if(!fixedFlag$sample90) {
					int reduceVar$numSales$6 = 0;
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						reduceVar$numSales$6 = (reduceVar$numSales$6 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$6 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				if(!fixedFlag$sample34) {
					double reduceVar$denom$11 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$11);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if((0 < numTimeSteps)) {
			if(system$gibbsForward) {
				if(!fixedFlag$sample34) {
					for(int var27 = 0; var27 < avail[0].length; var27 += 1)
						sample34(var27);
				}
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(!fixedFlag$sample88)
						sample88(t);
					if(!fixedFlag$sample90)
						sample90(t);
				}
			} else {
				for(int t = (numTimeSteps - 1); t >= 0; t -= 1) {
					if(!fixedFlag$sample90)
						sample90(t);
					if(!fixedFlag$sample88)
						sample88(t);
				}
				if(!fixedFlag$sample34) {
					for(int var27 = (avail[0].length - 1); var27 >= 0; var27 -= 1)
						sample34(var27);
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		numTimeSteps = avail.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var23 = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					logProbability$sample34[var27] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var77[t] = 0.0;
		}
		logProbability$lambda = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample88) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample88[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var79[t] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample90) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample90[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var111[t] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if((!fixedProbFlag$sample125 && (0 < numTimeSteps))) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$sample125[t] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		logProbabilityValue$sample125();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				double reduceVar$sum$11 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$11 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				if(!fixedFlag$sample90) {
					int reduceVar$numSales$8 = 0;
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						reduceVar$numSales$8 = (reduceVar$numSales$8 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$8 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				if(!fixedFlag$sample34) {
					double reduceVar$denom$13 = 0.0;
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$13);
				}
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		if((0 < numTimeSteps)) {
			int cv$length1 = sales.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				int[] cv$source2 = ObsSales[cv$index1];
				int[] cv$target2 = sales[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
			for(int t = (numTimeSteps - 1); t >= 0; t -= 1) {
				int[] observed_weekly_sales = sales[t];
				for(int j$var116 = (avail[0].length - 1); j$var116 >= 0; j$var116 -= 1)
					weekly_sales[t][j$var116] = observed_weekly_sales[j$var116];
			}
		}
	}

	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
				exped[j$var33] = Math.exp(ut[j$var33]);
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction50Index]);
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
				expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$12 * 0.3));
			for(int t = 0; t < numTimeSteps; t += 1) {
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86])
						weekly_ut[t][j$var86] = expedNorm[j$var86];
				}
				double reduceVar$denom$14 = 0.0;
				for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
					reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t][cv$reduction111Index]);
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
					weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$14);
				if(setFlag$weekly_sales) {
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
				}
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}