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
	private boolean fixedFlag$sample112 = false;
	private boolean fixedFlag$sample114 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedProbFlag$sample112 = false;
	private boolean fixedProbFlag$sample114 = false;
	private boolean fixedProbFlag$sample166 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean[] guard$sample32multinomial165$global;
	private boolean[][] guard$sample32put135$global;
	private boolean[][] guard$sample32put164$global;
	private boolean[] guard$sample32put73$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample112;
	private double[] logProbability$sample114;
	private double[] logProbability$sample166;
	private double[] logProbability$sample32;
	private double logProbability$ut;
	private double[] logProbability$var106;
	private double[] logProbability$var108;
	private double[] logProbability$var157;
	private double logProbability$var19;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private int[][] sales;
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
	public final boolean get$fixedFlag$sample112() {
		return fixedFlag$sample112;
	}

	@Override
	public final void set$fixedFlag$sample112(boolean cv$value) {
		fixedFlag$sample112 = cv$value;
		fixedProbFlag$sample112 = (cv$value && fixedProbFlag$sample112);
		fixedProbFlag$sample114 = (cv$value && fixedProbFlag$sample114);
	}

	@Override
	public final boolean get$fixedFlag$sample114() {
		return fixedFlag$sample114;
	}

	@Override
	public final void set$fixedFlag$sample114(boolean cv$value) {
		fixedFlag$sample114 = cv$value;
		fixedProbFlag$sample114 = (cv$value && fixedProbFlag$sample114);
		fixedProbFlag$sample166 = (cv$value && fixedProbFlag$sample166);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample166 = (cv$value && fixedProbFlag$sample166);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
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
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample166 = false;
	}

	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	private final void logProbabilityValue$sample112() {
		if(!fixedProbFlag$sample112) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(lambda[t], 10.0, 10.0);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var106[t] = cv$distributionAccumulator;
					logProbability$sample112[t] = cv$distributionAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample112 = fixedFlag$sample112;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample112[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var106[t] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample114() {
		if(!fixedProbFlag$sample114) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					int reduceVar$numSales$13 = 0;
					for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
						reduceVar$numSales$13 = (reduceVar$numSales$13 + ObsSales[t][cv$reduction100Index]);
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson((arrivals[t] - reduceVar$numSales$13), lambda[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var108[t] = cv$distributionAccumulator;
					logProbability$sample114[t] = cv$distributionAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample114 = (fixedFlag$sample114 && fixedFlag$sample112);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = logProbability$sample114[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var108[t] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample166() {
		if(!fixedProbFlag$sample166) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var157[t] = cv$distributionAccumulator;
					logProbability$sample166[t] = cv$distributionAccumulator;
					if((0 < avail[0].length))
						logProbability$sales = (logProbability$sales + cv$distributionAccumulator);
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample166 = (fixedFlag$sample32 && fixedFlag$sample114);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample166[t];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var157[t] = cv$sampleValue;
					if((0 < avail[0].length))
						logProbability$sales = (logProbability$sales + cv$sampleValue);
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$sampleAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(ut[var29]);
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					logProbability$sample32[var29] = cv$distributionAccumulator;
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
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_ut)) {
								cv$guard$weekly_ut = true;
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_ut && avail[t][var29])) {
							cv$guard$weekly_ut = true;
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
						}
					}
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var29])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var29])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
				}
				cv$accumulator = cv$sampleAccumulator;
				logProbability$var19 = cv$sampleAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					double cv$sampleValue = logProbability$sample32[var29];
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
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_ut)) {
								cv$guard$weekly_ut = true;
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_ut && avail[t][var29])) {
							cv$guard$weekly_ut = true;
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
						}
					}
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							if((avail[t][j$var66] && !cv$guard$weekly_rates)) {
								cv$guard$weekly_rates = true;
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var29])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((!cv$guard$weekly_rates && avail[t][var29])) {
							cv$guard$weekly_rates = true;
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
				}
				cv$accumulator = cv$rvAccumulator;
				logProbability$var19 = cv$rvAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample112(int t, int threadID$cv$t, Rng RNG$) {
		int reduceVar$numSales$9 = 0;
		for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
			reduceVar$numSales$9 = (reduceVar$numSales$9 + ObsSales[t][cv$reduction100Index]);
		lambda[t] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, (arrivals[t] - reduceVar$numSales$9), 1);
	}

	private final void sample114(int t, int threadID$cv$t, Rng RNG$) {
		int reduceVar$numSales$10 = 0;
		for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
			reduceVar$numSales$10 = (reduceVar$numSales$10 + ObsSales[t][cv$reduction100Index]);
		int cv$originalValue = (arrivals[t] - reduceVar$numSales$10);
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t]));
		int reduceVar$numSales$11 = 0;
		for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
			reduceVar$numSales$11 = (reduceVar$numSales$11 + ObsSales[t][cv$reduction100Index]);
		arrivals[t] = (reduceVar$numSales$11 + cv$proposedValue);
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			int reduceVar$numSales$12 = 0;
			for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
				reduceVar$numSales$12 = (reduceVar$numSales$12 + ObsSales[t][cv$reduction100Index]);
			arrivals[t] = (reduceVar$numSales$12 + cv$originalValue);
		}
	}

	private final void sample32(int var29) {
		double cv$originalValue = ut[var29];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66])
						guard$sample32multinomial165$global[t] = false;
				}
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66])
						guard$sample32multinomial165$global[t] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29])
					guard$sample32multinomial165$global[t] = false;
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29])
					guard$sample32multinomial165$global[t] = false;
			}
			if((0 < avail[0].length)) {
				for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((avail[t][j$var66] && !guard$sample32multinomial165$global[t])) {
							guard$sample32multinomial165$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						if((avail[t][j$var66] && !guard$sample32multinomial165$global[t])) {
							guard$sample32multinomial165$global[t] = true;
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample32multinomial165$global[t] && avail[t][var29])) {
					guard$sample32multinomial165$global[t] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample32multinomial165$global[t] && avail[t][var29])) {
					guard$sample32multinomial165$global[t] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var29] = cv$proposedValue;
		exped[var29] = Math.exp(ut[var29]);
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
			guard$sample32put73$global[j$var66] = false;
		guard$sample32put73$global[var29] = false;
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			if(!guard$sample32put73$global[j$var66]) {
				guard$sample32put73$global[j$var66] = true;
				double reduceVar$sum$13 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction54Index]);
				expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$13 * 0.3));
			}
		}
		if(!guard$sample32put73$global[var29]) {
			guard$sample32put73$global[var29] = true;
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction54Index]);
			expedNorm[var29] = (exped[var29] / (reduceVar$sum$14 * 0.3));
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66])
					guard$sample32put135$global[t][j$var66] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29])
				guard$sample32put135$global[t][var29] = false;
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var66] && !guard$sample32put135$global[t][j$var66])) {
					guard$sample32put135$global[t][j$var66] = true;
					weekly_ut[t][j$var66] = expedNorm[j$var66];
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample32put135$global[t][var29] && avail[t][var29])) {
				guard$sample32put135$global[t][var29] = true;
				weekly_ut[t][var29] = expedNorm[var29];
			}
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66]) {
					for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1)
						guard$sample32put164$global[t][j$var153] = false;
				}
			}
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66])
					guard$sample32put164$global[t][j$var66] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29]) {
				for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1)
					guard$sample32put164$global[t][j$var153] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29])
				guard$sample32put164$global[t][var29] = false;
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66]) {
					for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1) {
						if(!guard$sample32put164$global[t][j$var153]) {
							guard$sample32put164$global[t][j$var153] = true;
							double reduceVar$denom$15 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t][cv$reduction144Index]);
							weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$15);
						}
					}
				}
			}
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][j$var66] && !guard$sample32put164$global[t][j$var66])) {
					guard$sample32put164$global[t][j$var66] = true;
					double reduceVar$denom$16 = 0.0;
					for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
						reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t][cv$reduction144Index]);
					weekly_rates[t][j$var66] = (weekly_ut[t][j$var66] / reduceVar$denom$16);
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29]) {
				for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1) {
					if(!guard$sample32put164$global[t][j$var153]) {
						guard$sample32put164$global[t][j$var153] = true;
						double reduceVar$denom$17 = 0.0;
						for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
							reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t][cv$reduction144Index]);
						weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$17);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((avail[t][var29] && !guard$sample32put164$global[t][var29])) {
				guard$sample32put164$global[t][var29] = true;
				double reduceVar$denom$18 = 0.0;
				for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
					reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t][cv$reduction144Index]);
				weekly_rates[t][var29] = (weekly_ut[t][var29] / reduceVar$denom$18);
			}
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66])
					guard$sample32multinomial165$global[t] = false;
			}
		}
		for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][j$var66])
					guard$sample32multinomial165$global[t] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29])
				guard$sample32multinomial165$global[t] = false;
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if(avail[t][var29])
				guard$sample32multinomial165$global[t] = false;
		}
		if((0 < avail[0].length)) {
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var66] && !guard$sample32multinomial165$global[t])) {
						guard$sample32multinomial165$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var66] && !guard$sample32multinomial165$global[t])) {
						guard$sample32multinomial165$global[t] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample32multinomial165$global[t] && avail[t][var29])) {
				guard$sample32multinomial165$global[t] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			if((!guard$sample32multinomial165$global[t] && avail[t][var29])) {
				guard$sample32multinomial165$global[t] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], (avail[0].length + 1), arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var29] = cv$originalValue;
			exped[var29] = Math.exp(ut[var29]);
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
				guard$sample32put73$global[j$var66] = false;
			guard$sample32put73$global[var29] = false;
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				if(!guard$sample32put73$global[j$var66]) {
					guard$sample32put73$global[j$var66] = true;
					double reduceVar$sum$19 = 0.0;
					for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[cv$reduction54Index]);
					expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$19 * 0.3));
				}
			}
			if(!guard$sample32put73$global[var29]) {
				guard$sample32put73$global[var29] = true;
				double reduceVar$sum$20 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$20 = (reduceVar$sum$20 + exped[cv$reduction54Index]);
				expedNorm[var29] = (exped[var29] / (reduceVar$sum$20 * 0.3));
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66])
						guard$sample32put135$global[t][j$var66] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29])
					guard$sample32put135$global[t][var29] = false;
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var66] && !guard$sample32put135$global[t][j$var66])) {
						guard$sample32put135$global[t][j$var66] = true;
						weekly_ut[t][j$var66] = expedNorm[j$var66];
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((!guard$sample32put135$global[t][var29] && avail[t][var29])) {
					guard$sample32put135$global[t][var29] = true;
					weekly_ut[t][var29] = expedNorm[var29];
				}
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66]) {
						for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1)
							guard$sample32put164$global[t][j$var153] = false;
					}
				}
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66])
						guard$sample32put164$global[t][j$var66] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29]) {
					for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1)
						guard$sample32put164$global[t][j$var153] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29])
					guard$sample32put164$global[t][var29] = false;
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(avail[t][j$var66]) {
						for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1) {
							if(!guard$sample32put164$global[t][j$var153]) {
								guard$sample32put164$global[t][j$var153] = true;
								double reduceVar$denom$21 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$21 = (reduceVar$denom$21 + weekly_ut[t][cv$reduction144Index]);
								weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$21);
							}
						}
					}
				}
			}
			for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					if((avail[t][j$var66] && !guard$sample32put164$global[t][j$var66])) {
						guard$sample32put164$global[t][j$var66] = true;
						double reduceVar$denom$22 = 0.0;
						for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
							reduceVar$denom$22 = (reduceVar$denom$22 + weekly_ut[t][cv$reduction144Index]);
						weekly_rates[t][j$var66] = (weekly_ut[t][j$var66] / reduceVar$denom$22);
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(avail[t][var29]) {
					for(int j$var153 = 0; j$var153 <= avail[0].length; j$var153 += 1) {
						if(!guard$sample32put164$global[t][j$var153]) {
							guard$sample32put164$global[t][j$var153] = true;
							double reduceVar$denom$23 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
								reduceVar$denom$23 = (reduceVar$denom$23 + weekly_ut[t][cv$reduction144Index]);
							weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$23);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if((avail[t][var29] && !guard$sample32put164$global[t][var29])) {
					guard$sample32put164$global[t][var29] = true;
					double reduceVar$denom$24 = 0.0;
					for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
						reduceVar$denom$24 = (reduceVar$denom$24 + weekly_ut[t][cv$reduction144Index]);
					weekly_rates[t][var29] = (weekly_ut[t][var29] / reduceVar$denom$24);
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var66 = 0;
		if((0 < avail.length))
			cv$max_j$var66 = avail[0].length;
		guard$sample32put73$global = new boolean[cv$max_j$var66];
		{
			int cv$max_t = 0;
			int cv$max_j$var121 = 0;
			if((0 < avail.length)) {
				cv$max_j$var121 = avail[0].length;
				cv$max_t = avail.length;
			}
			guard$sample32put135$global = new boolean[cv$max_t][cv$max_j$var121];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var153 = 0;
			if((0 < avail.length)) {
				cv$max_j$var153 = (avail[0].length + 1);
				cv$max_t = avail.length;
			}
			guard$sample32put164$global = new boolean[cv$max_t][cv$max_j$var153];
		}
		int cv$max_t = 0;
		if((0 < avail.length))
			cv$max_t = avail.length;
		guard$sample32multinomial165$global = new boolean[cv$max_t];
	}

	@Override
	public final void allocator() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample32)
				ut = new double[avail[0].length];
			exped = new double[avail[0].length];
			expedNorm = new double[avail[0].length];
			sales = new int[avail.length][];
			for(int var80 = 0; var80 < avail.length; var80 += 1)
				sales[var80] = new int[avail[0].length];
		}
		if(!fixedFlag$sample112)
			lambda = new double[avail.length];
		if(!fixedFlag$sample114)
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
		if((0 < avail.length)) {
			for(int t = 0; t < avail.length; t += 1)
				weekly_sales[t] = new int[(avail[0].length + 1)];
		}
		weekly_sales = new int[avail.length][];
		logProbability$sample32 = new double[avail[0].length];
		logProbability$var106 = new double[avail.length];
		logProbability$sample112 = new double[avail.length];
		logProbability$var108 = new double[avail.length];
		logProbability$sample114 = new double[avail.length];
		logProbability$var157 = new double[avail.length];
		logProbability$sample166 = new double[avail.length];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample32) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								ut[var29] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1)
								exped[j$var41] = Math.exp(ut[j$var41]);
					}
				);
				double reduceVar$sum$21 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$21 = (reduceVar$sum$21 + exped[cv$reduction54Index]);
				double reduceVar$sum$21$1 = reduceVar$sum$21;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1)
								expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$21$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample114) {
								int reduceVar$numSales$14 = 0;
								for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
									reduceVar$numSales$14 = (reduceVar$numSales$14 + ObsSales[t][cv$reduction100Index]);
								arrivals[t] = (reduceVar$numSales$14 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[t][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[t][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample32) {
								double reduceVar$denom$25 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$25 = (reduceVar$denom$25 + weekly_ut[t][cv$reduction144Index]);
								double reduceVar$denom$25$2 = reduceVar$denom$25;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1)
												weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$25$2);
									}
								);
							}
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t], (avail[0].length + 1), arrivals[t], weekly_sales[t]);
							int[] observed_weekly_sales = sales[t];
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var168, int forEnd$j$var168, int threadID$j$var168, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var168 = forStart$j$var168; j$var168 < forEnd$j$var168; j$var168 += 1)
											observed_weekly_sales[j$var168] = weekly_sales[t][j$var168];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample32) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								ut[var29] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1)
								exped[j$var41] = Math.exp(ut[j$var41]);
					}
				);
				double reduceVar$sum$23 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$23 = (reduceVar$sum$23 + exped[cv$reduction54Index]);
				double reduceVar$sum$23$1 = reduceVar$sum$23;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1)
								expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$23$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample114) {
								int reduceVar$numSales$16 = 0;
								for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
									reduceVar$numSales$16 = (reduceVar$numSales$16 + ObsSales[t][cv$reduction100Index]);
								arrivals[t] = (reduceVar$numSales$16 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[t][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[t][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample32) {
								double reduceVar$denom$27 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$27 = (reduceVar$denom$27 + weekly_ut[t][cv$reduction144Index]);
								double reduceVar$denom$27$2 = reduceVar$denom$27;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1)
												weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$27$2);
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
			if(!fixedFlag$sample32) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								ut[var29] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1)
								exped[j$var41] = Math.exp(ut[j$var41]);
					}
				);
				double reduceVar$sum$22 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$22 = (reduceVar$sum$22 + exped[cv$reduction54Index]);
				double reduceVar$sum$22$1 = reduceVar$sum$22;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1)
								expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$22$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample114) {
								int reduceVar$numSales$15 = 0;
								for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
									reduceVar$numSales$15 = (reduceVar$numSales$15 + ObsSales[t][cv$reduction100Index]);
								arrivals[t] = (reduceVar$numSales$15 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[t][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[t][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample32) {
								double reduceVar$denom$26 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$26 = (reduceVar$denom$26 + weekly_ut[t][cv$reduction144Index]);
								double reduceVar$denom$26$2 = reduceVar$denom$26;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1)
												weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$26$2);
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
				if(!fixedFlag$sample32) {
					for(int var29 = 0; var29 < avail[0].length; var29 += 1)
						sample32(var29);
				}
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
							}
					}
				);
			} else {
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
							}
					}
				);
				if(!fixedFlag$sample32) {
					for(int var29 = (avail[0].length - 1); var29 >= 0; var29 -= 1)
						sample32(var29);
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
		logProbability$var19 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample32) {
				for(int var29 = 0; var29 < avail[0].length; var29 += 1)
					logProbability$sample32[var29] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var106[t] = 0.0;
		}
		logProbability$lambda = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample112) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample112[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var108[t] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample114) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample114[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var157[t] = 0.0;
		}
		logProbability$weekly_sales = 0.0;
		logProbability$sales = 0.0;
		if((!fixedProbFlag$sample166 && (0 < numTimeSteps))) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$sample166[t] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample112)
			logProbabilityValue$sample112();
		if(fixedFlag$sample114)
			logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			if(!fixedFlag$sample32) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								ut[var29] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1)
								exped[j$var41] = Math.exp(ut[j$var41]);
					}
				);
				double reduceVar$sum$24 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$24 = (reduceVar$sum$24 + exped[cv$reduction54Index]);
				double reduceVar$sum$24$1 = reduceVar$sum$24;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1)
								expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$24$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							if(!fixedFlag$sample114) {
								int reduceVar$numSales$17 = 0;
								for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1)
									reduceVar$numSales$17 = (reduceVar$numSales$17 + ObsSales[t][cv$reduction100Index]);
								arrivals[t] = (reduceVar$numSales$17 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[t][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[t][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(!fixedFlag$sample32) {
								double reduceVar$denom$28 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$28 = (reduceVar$denom$28 + weekly_ut[t][cv$reduction144Index]);
								double reduceVar$denom$28$2 = reduceVar$denom$28;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1)
												weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$28$2);
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
	public final void propagateObservedValues() {
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
				for(int j$var168 = (avail[0].length - 1); j$var168 >= 0; j$var168 -= 1)
					weekly_sales[t][j$var168] = sales[t][j$var168];
			}
		}
	}

	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			if(fixedFlag$sample32) {
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1)
								exped[j$var41] = Math.exp(ut[j$var41]);
					}
				);
				double reduceVar$sum$25 = 0.0;
				for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1)
					reduceVar$sum$25 = (reduceVar$sum$25 + exped[cv$reduction54Index]);
				double reduceVar$sum$25$1 = reduceVar$sum$25;
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1)
								expedNorm[j$var66] = (exped[j$var66] / (reduceVar$sum$25$1 * 0.3));
					}
				);
			}
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(fixedFlag$sample32)
													weekly_ut[t][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[t][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							if(fixedFlag$sample32) {
								double reduceVar$denom$29 = 0.0;
								for(int cv$reduction144Index = 0; cv$reduction144Index <= avail[0].length; cv$reduction144Index += 1)
									reduceVar$denom$29 = (reduceVar$denom$29 + weekly_ut[t][cv$reduction144Index]);
								double reduceVar$denom$29$2 = reduceVar$denom$29;
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1)
												weekly_rates[t][j$var153] = (weekly_ut[t][j$var153] / reduceVar$denom$29$2);
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
		     + "model Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n"
		     + "   // avail is the availability matrix, numTimeSteps-by-numProducts\n"
		     + "   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n"
		     + "   double r = 0.3;\n"
		     + "    \n"
		     + "   int numTimeSteps = avail.length;\n"
		     + "   if(numTimeSteps > 0) {\n"
		     + "      int numProducts = avail[0].length;\n"
		     + "\n"
		     + "      // draw utilities\n"
		     + "      double[] ut = gaussian(0, 1).sample(numProducts);\n"
		     + "\n"
		     + "      //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "      double[] exped = new double[numProducts];\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         exped[j] = exp(ut[j]);\n"
		     + "\n"
		     + "      //Choices includes the choice to not buy anything.\n"
		     + "      int numChoices = numProducts + 1;\n"
		     + "\n"
		     + "      //now normalize\n"
		     + "      double[] expedNorm = new double[numProducts];\n"
		     + "      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         expedNorm[j] = exped[j]/(r*sum);\n"
		     + "\n"
		     + "      int[][] sales = new int[numTimeSteps][numProducts];\n"
		     + "\n"
		     + "      for (int t:[0..numTimeSteps)){\n"
		     + "         // Calculate the number of purchases made.\n"
		     + "         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         // prior for the distribution of lambda for arrivals. These can be \n"
		     + "         // supplied as a vector if RGBU has some estimates, or just use some priors.\n"
		     + "         public double lambda = gamma(10,10).sample();\n"
		     + "         public int arrivals = numSales + poisson(lambda).sample();\n"
		     + "\n"
		     + "         // for each period t calculate choice probabilities and sales\n"
		     + "         double[] weekly_rates = new double[numChoices];\n"
		     + "         double[] weekly_ut = new double[numChoices];\n"
		     + "\n"
		     + "         for(int j : [0..numProducts)) {\n"
		     + "            if(avail[t][j])\n"
		     + "               weekly_ut[j] = expedNorm[j];\n"
		     + "            else\n"
		     + "               weekly_ut[j] = 0.0;\n"
		     + "         }\n"
		     + "         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n"
		     + "         // the model and delayed the assignment of the value 1 to here. None of this is a \n"
		     + "         // sandwood requirement, I just thought it made the model eaiser to follow.\n"
		     + "         weekly_ut[numProducts] = 1.0;\n"
		     + "\n"
		     + "         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         for(int j : [0..numProducts]) \n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "\n"
		     + "         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n"
		     + "\n"
		     + "         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n"
		     + "         int[] observed_weekly_sales = sales[t];\n"
		     + "         for (int j : [0..numProducts))\n"
		     + "            observed_weekly_sales[j] = weekly_sales[j] ;\n"
		     + "      }\n"
		     + "      // assert that generated sales match observed sales\n"
		     + "      sales.observe(ObsSales);\n"
		     + "   }\n"
		     + "}";
	}
}