package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basicDG$CoreInterface {
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean fixedProbFlag$sample89 = false;
	private boolean[] guard$sample34multinomial123$global;
	private boolean[][] guard$sample34put101$global;
	private boolean[][] guard$sample34put122$global;
	private boolean[] guard$sample34put61$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample124;
	private double[] logProbability$sample34;
	private double[] logProbability$sample87;
	private double[] logProbability$sample89;
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

	public Vulcano2012basicDG$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		fixedFlag$sample124 = cv$value;
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
	}

	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	@Override
	public final void set$fixedFlag$sample89(boolean cv$value) {
		fixedFlag$sample89 = cv$value;
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
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

	private final void logProbabilityValue$sample124() {
		if(!fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var111[t] = cv$distributionAccumulator;
					logProbability$sample124[t] = cv$distributionAccumulator;
					if((0 < avail[0].length))
						logProbability$sales = (logProbability$sales + cv$distributionAccumulator);
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((fixedFlag$sample124 && fixedFlag$sample34) && fixedFlag$sample89);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample124[t];
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

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(lambda[t], 10.0, 10.0);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var77[t] = cv$distributionAccumulator;
					logProbability$sample87[t] = cv$distributionAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = fixedFlag$sample87;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample87[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var77[t] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					int reduceVar$numSales$13 = 0;
					for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
						reduceVar$numSales$13 = (reduceVar$numSales$13 + ObsSales[t][cv$reduction77Index]);
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson((arrivals[t] - reduceVar$numSales$13), lambda[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var79[t] = cv$distributionAccumulator;
					logProbability$sample89[t] = cv$distributionAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedFlag$sample87);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample89[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var79[t] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
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
						guard$sample34multinomial123$global[t] = false;
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34multinomial123$global[t] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34multinomial123$global[t] = false;
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34multinomial123$global[t] = false;
			}
			if(((0 < exped.length) && (0 < avail[0].length))) {
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial123$global[t])) {
							guard$sample34multinomial123$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((avail[t][j$var50] && !guard$sample34multinomial123$global[t])) {
							guard$sample34multinomial123$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((((0 < weekly_ut[t].length) && !guard$sample34multinomial123$global[t]) && avail[t][var27])) {
					guard$sample34multinomial123$global[t] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample34multinomial123$global[t] && avail[t][var27])) {
					guard$sample34multinomial123$global[t] = true;
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
				double reduceVar$sum$13 = 0.0;
				for(int cv$reduction3042Index = 0; cv$reduction3042Index < avail[0].length; cv$reduction3042Index += 1)
					reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction3042Index]);
				expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$13 * 0.3));
			}
		}
		if(!guard$sample34put61$global[var27]) {
			guard$sample34put61$global[var27] = true;
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction50Index]);
			expedNorm[var27] = (exped[var27] / (reduceVar$sum$14 * 0.3));
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34put101$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34put101$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var50] && !guard$sample34put101$global[t][j$var50])) {
					guard$sample34put101$global[t][j$var50] = true;
					weekly_ut[t][j$var50] = expedNorm[j$var50];
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample34put101$global[t][var27] && avail[t][var27])) {
				guard$sample34put101$global[t][var27] = true;
				weekly_ut[t][var27] = expedNorm[var27];
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						guard$sample34put122$global[t][j$var107] = false;
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34put122$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
					guard$sample34put122$global[t][j$var107] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34put122$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						if(!guard$sample34put122$global[t][j$var107]) {
							guard$sample34put122$global[t][j$var107] = true;
							double reduceVar$denom$15 = 0.0;
							for(int cv$reduction3477Index = 0; cv$reduction3477Index <= avail[0].length; cv$reduction3477Index += 1)
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t][cv$reduction3477Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$15);
						}
					}
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var50] && !guard$sample34put122$global[t][j$var50])) {
					guard$sample34put122$global[t][j$var50] = true;
					double reduceVar$denom$16 = 0.0;
					for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
						reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t][cv$reduction110Index]);
					weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$16);
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
					if(!guard$sample34put122$global[t][j$var107]) {
						guard$sample34put122$global[t][j$var107] = true;
						double reduceVar$denom$17 = 0.0;
						for(int cv$reduction3577Index = 0; cv$reduction3577Index <= avail[0].length; cv$reduction3577Index += 1)
							reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t][cv$reduction3577Index]);
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$17);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((avail[t][var27] && !guard$sample34put122$global[t][var27])) {
				guard$sample34put122$global[t][var27] = true;
				double reduceVar$denom$18 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
					reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t][cv$reduction110Index]);
				weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$18);
			}
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34multinomial123$global[t] = false;
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var50])
					guard$sample34multinomial123$global[t] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34multinomial123$global[t] = false;
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var27])
				guard$sample34multinomial123$global[t] = false;
		}
		if(((0 < exped.length) && (0 < avail[0].length))) {
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial123$global[t])) {
						guard$sample34multinomial123$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34multinomial123$global[t])) {
						guard$sample34multinomial123$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((((0 < weekly_ut[t].length) && !guard$sample34multinomial123$global[t]) && avail[t][var27])) {
				guard$sample34multinomial123$global[t] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample34multinomial123$global[t] && avail[t][var27])) {
				guard$sample34multinomial123$global[t] = true;
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
					double reduceVar$sum$19 = 0.0;
					for(int cv$reduction4215Index = 0; cv$reduction4215Index < avail[0].length; cv$reduction4215Index += 1)
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[cv$reduction4215Index]);
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$19 * 0.3));
				}
			}
			if(!guard$sample34put61$global[var27]) {
				guard$sample34put61$global[var27] = true;
				double reduceVar$sum$20 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$20 = (reduceVar$sum$20 + exped[cv$reduction50Index]);
				expedNorm[var27] = (exped[var27] / (reduceVar$sum$20 * 0.3));
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34put101$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34put101$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34put101$global[t][j$var50])) {
						guard$sample34put101$global[t][j$var50] = true;
						weekly_ut[t][j$var50] = expedNorm[j$var50];
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample34put101$global[t][var27] && avail[t][var27])) {
					guard$sample34put101$global[t][var27] = true;
					weekly_ut[t][var27] = expedNorm[var27];
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
							guard$sample34put122$global[t][j$var107] = false;
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50])
						guard$sample34put122$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						guard$sample34put122$global[t][j$var107] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27])
					guard$sample34put122$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
							if(!guard$sample34put122$global[t][j$var107]) {
								guard$sample34put122$global[t][j$var107] = true;
								double reduceVar$denom$21 = 0.0;
								for(int cv$reduction4650Index = 0; cv$reduction4650Index <= avail[0].length; cv$reduction4650Index += 1)
									reduceVar$denom$21 = (reduceVar$denom$21 + weekly_ut[t][cv$reduction4650Index]);
								weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$21);
							}
						}
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var50] && !guard$sample34put122$global[t][j$var50])) {
						guard$sample34put122$global[t][j$var50] = true;
						double reduceVar$denom$22 = 0.0;
						for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
							reduceVar$denom$22 = (reduceVar$denom$22 + weekly_ut[t][cv$reduction110Index]);
						weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$22);
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						if(!guard$sample34put122$global[t][j$var107]) {
							guard$sample34put122$global[t][j$var107] = true;
							double reduceVar$denom$23 = 0.0;
							for(int cv$reduction4750Index = 0; cv$reduction4750Index <= avail[0].length; cv$reduction4750Index += 1)
								reduceVar$denom$23 = (reduceVar$denom$23 + weekly_ut[t][cv$reduction4750Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$23);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][var27] && !guard$sample34put122$global[t][var27])) {
					guard$sample34put122$global[t][var27] = true;
					double reduceVar$denom$24 = 0.0;
					for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
						reduceVar$denom$24 = (reduceVar$denom$24 + weekly_ut[t][cv$reduction110Index]);
					weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$24);
				}
			}
		}
	}

	private final void sample87(int t, int threadID$cv$t, Rng RNG$) {
		int reduceVar$numSales$9 = 0;
		for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
			reduceVar$numSales$9 = (reduceVar$numSales$9 + ObsSales[t][cv$reduction77Index]);
		lambda[t] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, (arrivals[t] - reduceVar$numSales$9), 1);
	}

	private final void sample89(int t, int threadID$cv$t, Rng RNG$) {
		int reduceVar$numSales$10 = 0;
		for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
			reduceVar$numSales$10 = (reduceVar$numSales$10 + ObsSales[t][cv$reduction77Index]);
		int cv$originalValue = (arrivals[t] - reduceVar$numSales$10);
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t]));
		int reduceVar$numSales$11 = 0;
		for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
			reduceVar$numSales$11 = (reduceVar$numSales$11 + ObsSales[t][cv$reduction77Index]);
		arrivals[t] = (reduceVar$numSales$11 + cv$proposedValue);
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			int reduceVar$numSales$12 = 0;
			for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
				reduceVar$numSales$12 = (reduceVar$numSales$12 + ObsSales[t][cv$reduction77Index]);
			arrivals[t] = (reduceVar$numSales$12 + cv$originalValue);
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
			guard$sample34put101$global = new boolean[cv$max_t][cv$max_j$var86];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var107 = 0;
			if((0 < avail.length)) {
				cv$max_j$var107 = (avail[0].length + 1);
				cv$max_t = avail.length;
			}
			guard$sample34put122$global = new boolean[cv$max_t][cv$max_j$var107];
		}
		int cv$max_t = 0;
		if((0 < avail.length))
			cv$max_t = avail.length;
		guard$sample34multinomial123$global = new boolean[cv$max_t];
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
		logProbability$sample87 = new double[avail.length];
		logProbability$var79 = new double[avail.length];
		logProbability$sample89 = new double[avail.length];
		logProbability$var111 = new double[avail.length];
		logProbability$sample124 = new double[avail.length];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								ut[var27] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var33, int forEnd$j$var33, int threadID$j$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var33 = forStart$j$var33; j$var33 < forEnd$j$var33; j$var33 += 1)
								exped[j$var33] = Math.exp(ut[j$var33]);
					}
				);
				double reduceVar$sum$21 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$21 = (reduceVar$sum$21 + exped[cv$reduction50Index]);
				double reduceVar$sum$21$1 = reduceVar$sum$21;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$21$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							if(!fixedFlag$sample87)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample89) {
								int reduceVar$numSales$14 = 0;
								for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
									reduceVar$numSales$14 = (reduceVar$numSales$14 + ObsSales[t][cv$reduction77Index]);
								arrivals[t] = (reduceVar$numSales$14 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var86, int forEnd$j$var86, int threadID$j$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var86 = forStart$j$var86; j$var86 < forEnd$j$var86; j$var86 += 1) {
											if(avail[t][j$var86]) {
												if(!fixedFlag$sample34)
													weekly_ut[t][j$var86] = expedNorm[j$var86];
											} else
												weekly_ut[t][j$var86] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample34) {
								double reduceVar$denom$25 = 0.0;
								for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
									reduceVar$denom$25 = (reduceVar$denom$25 + weekly_ut[t][cv$reduction110Index]);
								double reduceVar$denom$25$2 = reduceVar$denom$25;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var107, int forEnd$j$var107, int threadID$j$var107, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var107 = forStart$j$var107; j$var107 < forEnd$j$var107; j$var107 += 1)
												weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$25$2);
									}
								);
							}
							if(!fixedFlag$sample124) {
								DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t], arrivals[t], weekly_sales[t]);
								int[] observed_weekly_sales = sales[t];
								parallelFor(RNG$1, 0, avail[0].length, 1,
									(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
												observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
									}
								);
							}
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								ut[var27] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var33, int forEnd$j$var33, int threadID$j$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var33 = forStart$j$var33; j$var33 < forEnd$j$var33; j$var33 += 1)
								exped[j$var33] = Math.exp(ut[j$var33]);
					}
				);
				double reduceVar$sum$23 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$23 = (reduceVar$sum$23 + exped[cv$reduction50Index]);
				double reduceVar$sum$23$1 = reduceVar$sum$23;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$23$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							if(!fixedFlag$sample87)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample89) {
								int reduceVar$numSales$16 = 0;
								for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
									reduceVar$numSales$16 = (reduceVar$numSales$16 + ObsSales[t][cv$reduction77Index]);
								arrivals[t] = (reduceVar$numSales$16 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var86, int forEnd$j$var86, int threadID$j$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var86 = forStart$j$var86; j$var86 < forEnd$j$var86; j$var86 += 1) {
											if(avail[t][j$var86]) {
												if(!fixedFlag$sample34)
													weekly_ut[t][j$var86] = expedNorm[j$var86];
											} else
												weekly_ut[t][j$var86] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample34) {
								double reduceVar$denom$27 = 0.0;
								for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
									reduceVar$denom$27 = (reduceVar$denom$27 + weekly_ut[t][cv$reduction110Index]);
								double reduceVar$denom$27$2 = reduceVar$denom$27;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var107, int forEnd$j$var107, int threadID$j$var107, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var107 = forStart$j$var107; j$var107 < forEnd$j$var107; j$var107 += 1)
												weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$27$2);
									}
								);
							}
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								ut[var27] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var33, int forEnd$j$var33, int threadID$j$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var33 = forStart$j$var33; j$var33 < forEnd$j$var33; j$var33 += 1)
								exped[j$var33] = Math.exp(ut[j$var33]);
					}
				);
				double reduceVar$sum$22 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$22 = (reduceVar$sum$22 + exped[cv$reduction50Index]);
				double reduceVar$sum$22$1 = reduceVar$sum$22;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$22$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							if(!fixedFlag$sample87)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample89) {
								int reduceVar$numSales$15 = 0;
								for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
									reduceVar$numSales$15 = (reduceVar$numSales$15 + ObsSales[t][cv$reduction77Index]);
								arrivals[t] = (reduceVar$numSales$15 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var86, int forEnd$j$var86, int threadID$j$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var86 = forStart$j$var86; j$var86 < forEnd$j$var86; j$var86 += 1) {
											if(avail[t][j$var86]) {
												if(!fixedFlag$sample34)
													weekly_ut[t][j$var86] = expedNorm[j$var86];
											} else
												weekly_ut[t][j$var86] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample34) {
								double reduceVar$denom$26 = 0.0;
								for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
									reduceVar$denom$26 = (reduceVar$denom$26 + weekly_ut[t][cv$reduction110Index]);
								double reduceVar$denom$26$2 = reduceVar$denom$26;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var107, int forEnd$j$var107, int threadID$j$var107, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var107 = forStart$j$var107; j$var107 < forEnd$j$var107; j$var107 += 1)
												weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$26$2);
									}
								);
							}
						}
				}
			);
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
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample87)
									sample87(t, threadID$t, RNG$1);
								if(!fixedFlag$sample89)
									sample89(t, threadID$t, RNG$1);
							}
					}
				);
			} else {
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample89)
									sample89(t, threadID$t, RNG$1);
								if(!fixedFlag$sample87)
									sample87(t, threadID$t, RNG$1);
							}
					}
				);
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
			if(!fixedProbFlag$sample87) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample87[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var79[t] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample89) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample89[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var111[t] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if((!fixedProbFlag$sample124 && (0 < numTimeSteps))) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$sample124[t] = 0.0;
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
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		if(fixedFlag$sample89)
			logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample87();
		logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample87();
		logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample34) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								ut[var27] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var33, int forEnd$j$var33, int threadID$j$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var33 = forStart$j$var33; j$var33 < forEnd$j$var33; j$var33 += 1)
								exped[j$var33] = Math.exp(ut[j$var33]);
					}
				);
				double reduceVar$sum$24 = 0.0;
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					reduceVar$sum$24 = (reduceVar$sum$24 + exped[cv$reduction50Index]);
				double reduceVar$sum$24$1 = reduceVar$sum$24;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$24$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							if(!fixedFlag$sample87)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample89) {
								int reduceVar$numSales$17 = 0;
								for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1)
									reduceVar$numSales$17 = (reduceVar$numSales$17 + ObsSales[t][cv$reduction77Index]);
								arrivals[t] = (reduceVar$numSales$17 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var86, int forEnd$j$var86, int threadID$j$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var86 = forStart$j$var86; j$var86 < forEnd$j$var86; j$var86 += 1) {
											if(avail[t][j$var86]) {
												if(!fixedFlag$sample34)
													weekly_ut[t][j$var86] = expedNorm[j$var86];
											} else
												weekly_ut[t][j$var86] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample34) {
								double reduceVar$denom$28 = 0.0;
								for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
									reduceVar$denom$28 = (reduceVar$denom$28 + weekly_ut[t][cv$reduction110Index]);
								double reduceVar$denom$28$2 = reduceVar$denom$28;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var107, int forEnd$j$var107, int threadID$j$var107, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var107 = forStart$j$var107; j$var107 < forEnd$j$var107; j$var107 += 1)
												weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$28$2);
									}
								);
							}
						}
				}
			);
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
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var33, int forEnd$j$var33, int threadID$j$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var33 = forStart$j$var33; j$var33 < forEnd$j$var33; j$var33 += 1)
							exped[j$var33] = Math.exp(ut[j$var33]);
				}
			);
			double reduceVar$sum$25 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				reduceVar$sum$25 = (reduceVar$sum$25 + exped[cv$reduction50Index]);
			double reduceVar$sum$25$1 = reduceVar$sum$25;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
							expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$25$1 * 0.3));
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var86, int forEnd$j$var86, int threadID$j$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var86 = forStart$j$var86; j$var86 < forEnd$j$var86; j$var86 += 1) {
											if(avail[t][j$var86])
												weekly_ut[t][j$var86] = expedNorm[j$var86];
										}
								}
							);
							double reduceVar$denom$29 = 0.0;
							for(int cv$reduction110Index = 0; cv$reduction110Index <= avail[0].length; cv$reduction110Index += 1)
								reduceVar$denom$29 = (reduceVar$denom$29 + weekly_ut[t][cv$reduction110Index]);
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var107, int forEnd$j$var107, int threadID$j$var107, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var107 = forStart$j$var107; j$var107 < forEnd$j$var107; j$var107 += 1)
											weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$29$2);
								}
							);
							if(setFlag$weekly_sales) {
								int[] observed_weekly_sales = sales[t];
								parallelFor(RNG$1, 0, avail[0].length, 1,
									(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
												observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
									}
								);
							}
						}
				}
			);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}