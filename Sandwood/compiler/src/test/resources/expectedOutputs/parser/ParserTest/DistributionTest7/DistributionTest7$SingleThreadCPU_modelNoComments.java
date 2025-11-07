package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest7$CoreInterface {
	private double[] bias;
	private int cat;
	private double[] cv$var31$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double data;
	private double[] distribution$sample31;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample51 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cat;
	private double logProbability$data;
	private double logProbability$result;
	private double logProbability$sample45;
	private double logProbability$var43;
	private double observedData;
	private double[] prob;
	private int result;
	private boolean system$gibbsForward = true;
	private int var43;

	public DistributionTest7$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final int get$cat() {
		return cat;
	}

	@Override
	public final void set$cat(int cv$value) {
		cat = cv$value;
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample51 = false;
	}

	@Override
	public final double get$data() {
		return data;
	}

	@Override
	public final double[] get$distribution$sample31() {
		return distribution$sample31;
	}

	@Override
	public final void set$distribution$sample31(double[] cv$value) {
		distribution$sample31 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample45 = (fixedFlag$sample31 && fixedProbFlag$sample45);
		fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample51 = (fixedFlag$sample45 && fixedProbFlag$sample51);
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
	public final double get$logProbability$cat() {
		return logProbability$cat;
	}

	@Override
	public final double get$logProbability$data() {
		return logProbability$data;
	}

	@Override
	public final double get$logProbability$result() {
		return logProbability$result;
	}

	@Override
	public final double get$observedData() {
		return observedData;
	}

	@Override
	public final void set$observedData(double cv$value) {
		observedData = cv$value;
	}

	@Override
	public final double[] get$prob() {
		return prob;
	}

	@Override
	public final int get$result() {
		return result;
	}

	@Override
	public final int get$var43() {
		return var43;
	}

	@Override
	public final void set$var43(int cv$value) {
		var43 = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample51 = false;
	}

	private final void logProbabilityDistribution$sample31() {
		if(!fixedProbFlag$sample31) {
			if(fixedFlag$sample31) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = cat;
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 3))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$cat = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample31)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample31 = fixedFlag$sample31;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = var43;
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$7_1 = 0.2;
									if((0 == cat)) {
										{
											double var40 = traceTempVariable$var40$7_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
									int distributionTempVariable$cat$5 = index$sample31$3;
									double cv$probabilitySample31Value4 = (1.0 * distribution$sample31[index$sample31$3]);
									{
										{
											double traceTempVariable$var40$8_1 = 0.2;
											if((0 == distributionTempVariable$cat$5)) {
												{
													double var40 = traceTempVariable$var40$8_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$14_1 = 0.3;
									if((1 == cat)) {
										{
											double var40 = traceTempVariable$var40$14_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$10 = 0; index$sample31$10 < 3; index$sample31$10 += 1) {
									int distributionTempVariable$cat$12 = index$sample31$10;
									double cv$probabilitySample31Value11 = (1.0 * distribution$sample31[index$sample31$10]);
									{
										{
											double traceTempVariable$var40$15_1 = 0.3;
											if((1 == distributionTempVariable$cat$12)) {
												{
													double var40 = traceTempVariable$var40$15_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value11);
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$21_1 = 0.5;
									if((2 == cat)) {
										{
											double var40 = traceTempVariable$var40$21_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$17 = 0; index$sample31$17 < 3; index$sample31$17 += 1) {
									int distributionTempVariable$cat$19 = index$sample31$17;
									double cv$probabilitySample31Value18 = (1.0 * distribution$sample31[index$sample31$17]);
									{
										{
											double traceTempVariable$var40$22_1 = 0.5;
											if((2 == distributionTempVariable$cat$19)) {
												{
													double var40 = traceTempVariable$var40$22_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
												}
											}
										}
									}
								}
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
				logProbability$sample45 = cv$sampleProbability;
				boolean cv$guard$result = false;
				{
					{
						if(!(cat == 1)) {
							if((fixedFlag$sample31 && fixedFlag$sample45)) {
								if(!cv$guard$result) {
									cv$guard$result = true;
									logProbability$result = (logProbability$result + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$result = false;
				{
					{
						if(!(cat == 1)) {
							if((fixedFlag$sample31 && fixedFlag$sample45)) {
								if(!cv$guard$result) {
									cv$guard$result = true;
									logProbability$result = (logProbability$result + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = data;
					if(fixedFlag$sample31) {
						{
							{
								if(!(cat == 1)) {
									int traceTempVariable$result$7_1 = var43;
									{
										{
											double var47 = (double)traceTempVariable$result$7_1;
											double var46 = (double)cat;
											double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
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
						}
					} else {
						if(true) {
							for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
								int distributionTempVariable$cat$5 = index$sample31$3;
								double cv$probabilitySample31Value4 = (1.0 * distribution$sample31[index$sample31$3]);
								{
									{
										if(!(distributionTempVariable$cat$5 == 1)) {
											int traceTempVariable$result$8_1 = var43;
											{
												{
													double var47 = (double)traceTempVariable$result$8_1;
													double var46 = (double)distributionTempVariable$cat$5;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
											if(!true) {
												for(int index$sample31$11 = 0; index$sample31$11 < 3; index$sample31$11 += 1) {
													int distributionTempVariable$cat$13 = index$sample31$11;
													double cv$probabilitySample31Value12 = (cv$probabilitySample31Value4 * distribution$sample31[index$sample31$11]);
													{
														{
															double var47 = (double)traceTempVariable$result$8_1;
															double var46 = (double)distributionTempVariable$cat$13;
															double cv$weightedProbability = (Math.log(cv$probabilitySample31Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value12);
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$data = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = cat;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 3))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$cat = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = var43;
						{
							{
								double var40 = bias[cat];
								int var41 = 10;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
				logProbability$sample45 = cv$sampleProbability;
				boolean cv$guard$result = false;
				{
					{
						if(!(cat == 1)) {
							if(!cv$guard$result) {
								cv$guard$result = true;
								logProbability$result = (logProbability$result + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$result = false;
				{
					{
						if(!(cat == 1)) {
							if(!cv$guard$result) {
								cv$guard$result = true;
								logProbability$result = (logProbability$result + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = data;
					{
						{
							double var47 = (double)result;
							double var46 = (double)cat;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
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
			logProbability$data = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31() {
		if(true) {
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 3);
			}
			double[] cv$stateProbabilityLocal = cv$var31$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 3))?Math.log(prob[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(!(cv$currentValue == 1)) {
									int traceTempVariable$cat$1_1 = cv$currentValue;
									{
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													double traceTempVariable$var40$3_1 = 0.2;
													if((0 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												{
													double traceTempVariable$var40$4_1 = 0.3;
													if((1 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												{
													double traceTempVariable$var40$5_1 = 0.5;
													if((2 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)));
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
							{
								int traceTempVariable$cat$9_1 = cv$currentValue;
								{
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												int traceTempVariable$cat$11_1 = cv$currentValue;
												{
													if(!(traceTempVariable$cat$11_1 == 1)) {
														int traceTempVariable$result$16_1 = var43;
														{
															{
																{
																	double var47 = (double)traceTempVariable$result$16_1;
																	double var46 = (double)traceTempVariable$cat$11_1;
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
											}
											if(!true) {
												for(int index$sample31$12 = 0; index$sample31$12 < 3; index$sample31$12 += 1) {
													int distributionTempVariable$cat$14 = index$sample31$12;
													double cv$probabilitySample31Value13 = (1.0 * distribution$sample31[index$sample31$12]);
													{
														int traceTempVariable$cat$15_1 = distributionTempVariable$cat$14;
														{
															if(!(traceTempVariable$cat$15_1 == 1)) {
																int traceTempVariable$result$17_1 = var43;
																{
																	{
																		{
																			double var47 = (double)traceTempVariable$result$17_1;
																			double var46 = (double)traceTempVariable$cat$15_1;
																			if(((Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value13);
																		}
																	}
																}
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
					{
						{
							{
								{
									{
										{
											int traceTempVariable$cat$21_1 = cv$currentValue;
											{
												if((traceTempVariable$cat$21_1 == 1)) {
													int traceTempVariable$result$26_1 = 5;
													{
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		if(!(traceTempVariable$cat$21_1 == 1)) {
																			int traceTempVariable$result$30_1 = var43;
																			{
																				int traceTempVariable$cat$31_1 = cv$currentValue;
																				{
																					{
																						{
																							double var47 = (double)traceTempVariable$result$30_1;
																							double var46 = (double)traceTempVariable$cat$31_1;
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample31$32 = 0; index$sample31$32 < 3; index$sample31$32 += 1) {
																					int distributionTempVariable$cat$34 = index$sample31$32;
																					double cv$probabilitySample31Value33 = (1.0 * distribution$sample31[index$sample31$32]);
																					{
																						int traceTempVariable$cat$35_1 = distributionTempVariable$cat$34;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$30_1;
																									double var46 = (double)traceTempVariable$cat$35_1;
																									if(((Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value33);
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
										if(!true) {
											for(int index$sample31$22 = 0; index$sample31$22 < 3; index$sample31$22 += 1) {
												int distributionTempVariable$cat$24 = index$sample31$22;
												double cv$probabilitySample31Value23 = (1.0 * distribution$sample31[index$sample31$22]);
												{
													int traceTempVariable$cat$25_1 = distributionTempVariable$cat$24;
													{
														if((traceTempVariable$cat$25_1 == 1)) {
															int traceTempVariable$result$27_1 = 5;
															{
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				if(!(traceTempVariable$cat$25_1 == 1)) {
																					int traceTempVariable$result$36_1 = var43;
																					{
																						int traceTempVariable$cat$37_1 = cv$currentValue;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$36_1;
																									double var46 = (double)traceTempVariable$cat$37_1;
																									if(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$38_1 = distributionTempVariable$cat$24;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$36_1;
																									double var46 = (double)traceTempVariable$cat$38_1;
																									if(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample31$39 = 0; index$sample31$39 < 3; index$sample31$39 += 1) {
																							int distributionTempVariable$cat$41 = index$sample31$39;
																							double cv$probabilitySample31Value40 = (cv$probabilitySample31Value23 * distribution$sample31[index$sample31$39]);
																							{
																								int traceTempVariable$cat$42_1 = distributionTempVariable$cat$41;
																								{
																									{
																										{
																											double var47 = (double)traceTempVariable$result$36_1;
																											double var46 = (double)traceTempVariable$cat$42_1;
																											if(((Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value40);
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
									{
										{
											int traceTempVariable$cat$48_1 = cv$currentValue;
											{
												if(!(traceTempVariable$cat$48_1 == 1)) {
													int traceTempVariable$result$53_1 = var43;
													{
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		if(!(traceTempVariable$cat$48_1 == 1)) {
																			int traceTempVariable$result$57_1 = var43;
																			{
																				int traceTempVariable$cat$58_1 = cv$currentValue;
																				{
																					{
																						{
																							double var47 = (double)traceTempVariable$result$57_1;
																							double var46 = (double)traceTempVariable$cat$58_1;
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample31$59 = 0; index$sample31$59 < 3; index$sample31$59 += 1) {
																					int distributionTempVariable$cat$61 = index$sample31$59;
																					double cv$probabilitySample31Value60 = (1.0 * distribution$sample31[index$sample31$59]);
																					{
																						int traceTempVariable$cat$62_1 = distributionTempVariable$cat$61;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$57_1;
																									double var46 = (double)traceTempVariable$cat$62_1;
																									if(((Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value60);
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
										if(!true) {
											for(int index$sample31$49 = 0; index$sample31$49 < 3; index$sample31$49 += 1) {
												int distributionTempVariable$cat$51 = index$sample31$49;
												double cv$probabilitySample31Value50 = (1.0 * distribution$sample31[index$sample31$49]);
												{
													int traceTempVariable$cat$52_1 = distributionTempVariable$cat$51;
													{
														if(!(traceTempVariable$cat$52_1 == 1)) {
															int traceTempVariable$result$54_1 = var43;
															{
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				if(!(traceTempVariable$cat$52_1 == 1)) {
																					int traceTempVariable$result$63_1 = var43;
																					{
																						int traceTempVariable$cat$64_1 = cv$currentValue;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$63_1;
																									double var46 = (double)traceTempVariable$cat$64_1;
																									if(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$65_1 = distributionTempVariable$cat$51;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$63_1;
																									double var46 = (double)traceTempVariable$cat$65_1;
																									if(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample31$66 = 0; index$sample31$66 < 3; index$sample31$66 += 1) {
																							int distributionTempVariable$cat$68 = index$sample31$66;
																							double cv$probabilitySample31Value67 = (cv$probabilitySample31Value50 * distribution$sample31[index$sample31$66]);
																							{
																								int traceTempVariable$cat$69_1 = distributionTempVariable$cat$68;
																								{
																									{
																										{
																											double var47 = (double)traceTempVariable$result$63_1;
																											double var46 = (double)traceTempVariable$cat$69_1;
																											if(((Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value67);
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
									{
										{
											if(!(cv$currentValue == 1)) {
												{
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	int traceTempVariable$cat$77_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$82_1 = 0.2;
																		if((0 == traceTempVariable$cat$77_1)) {
																			{
																				double var40 = bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$78 = 0; index$sample31$78 < 3; index$sample31$78 += 1) {
																		int distributionTempVariable$cat$80 = index$sample31$78;
																		double cv$probabilitySample31Value79 = (1.0 * distribution$sample31[index$sample31$78]);
																		{
																			int traceTempVariable$cat$81_1 = distributionTempVariable$cat$80;
																			{
																				double traceTempVariable$var40$83_1 = 0.2;
																				if((0 == traceTempVariable$cat$81_1)) {
																					{
																						double var40 = bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value79);
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$cat$84_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$89_1 = 0.3;
																		if((1 == traceTempVariable$cat$84_1)) {
																			{
																				double var40 = bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$85 = 0; index$sample31$85 < 3; index$sample31$85 += 1) {
																		int distributionTempVariable$cat$87 = index$sample31$85;
																		double cv$probabilitySample31Value86 = (1.0 * distribution$sample31[index$sample31$85]);
																		{
																			int traceTempVariable$cat$88_1 = distributionTempVariable$cat$87;
																			{
																				double traceTempVariable$var40$90_1 = 0.3;
																				if((1 == traceTempVariable$cat$88_1)) {
																					{
																						double var40 = bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value86);
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$cat$91_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$96_1 = 0.5;
																		if((2 == traceTempVariable$cat$91_1)) {
																			{
																				double var40 = bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$92 = 0; index$sample31$92 < 3; index$sample31$92 += 1) {
																		int distributionTempVariable$cat$94 = index$sample31$92;
																		double cv$probabilitySample31Value93 = (1.0 * distribution$sample31[index$sample31$92]);
																		{
																			int traceTempVariable$cat$95_1 = distributionTempVariable$cat$94;
																			{
																				double traceTempVariable$var40$97_1 = 0.5;
																				if((2 == traceTempVariable$cat$95_1)) {
																					{
																						double var40 = bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value93);
																					}
																				}
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
			double[] cv$localProbability = distribution$sample31;
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample45() {
		if(true) {
			int cv$numStates = 0;
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$6_1 = 0.2;
						if((0 == cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$2 = 0; index$sample31$2 < 3; index$sample31$2 += 1) {
						int distributionTempVariable$cat$4 = index$sample31$2;
						double cv$probabilitySample31Value3 = (1.0 * distribution$sample31[index$sample31$2]);
						{
							{
								double traceTempVariable$var40$7_1 = 0.2;
								if((0 == distributionTempVariable$cat$4))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$13_1 = 0.3;
						if((1 == cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$9 = 0; index$sample31$9 < 3; index$sample31$9 += 1) {
						int distributionTempVariable$cat$11 = index$sample31$9;
						double cv$probabilitySample31Value10 = (1.0 * distribution$sample31[index$sample31$9]);
						{
							{
								double traceTempVariable$var40$14_1 = 0.3;
								if((1 == distributionTempVariable$cat$11))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$20_1 = 0.5;
						if((2 == cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$16 = 0; index$sample31$16 < 3; index$sample31$16 += 1) {
						int distributionTempVariable$cat$18 = index$sample31$16;
						double cv$probabilitySample31Value17 = (1.0 * distribution$sample31[index$sample31$16]);
						{
							{
								double traceTempVariable$var40$21_1 = 0.5;
								if((2 == distributionTempVariable$cat$18))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var43$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				var43 = cv$currentValue;
				{
					{
						if(!(cat == 1)) {
							{
								result = cv$currentValue;
							}
						}
					}
				}
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$28_1 = 0.2;
							if((0 == cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$28_1, 10));
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$45_1 = cv$currentValue;
													{
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double var47 = (double)traceTempVariable$result$45_1;
																				double var46 = (double)cat;
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$24 = 0; index$sample31$24 < 3; index$sample31$24 += 1) {
							int distributionTempVariable$cat$26 = index$sample31$24;
							double cv$probabilitySample31Value25 = (1.0 * distribution$sample31[index$sample31$24]);
							{
								{
									double traceTempVariable$var40$29_1 = 0.2;
									if((0 == distributionTempVariable$cat$26)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value25);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value25) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$29_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$26 == 1)) {
															int traceTempVariable$result$51_1 = cv$currentValue;
															{
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			int traceTempVariable$cat$81_1 = distributionTempVariable$cat$26;
																			{
																				{
																					{
																						double var47 = (double)traceTempVariable$result$51_1;
																						double var46 = (double)traceTempVariable$cat$81_1;
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample31$82 = 0; index$sample31$82 < 3; index$sample31$82 += 1) {
																				int distributionTempVariable$cat$84 = index$sample31$82;
																				double cv$probabilitySample31Value83 = (1.0 * distribution$sample31[index$sample31$82]);
																				{
																					int traceTempVariable$cat$85_1 = distributionTempVariable$cat$84;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$51_1;
																								double var46 = (double)traceTempVariable$cat$85_1;
																								if(((Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value83);
																							}
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
												if(!true) {
													for(int index$sample31$47 = 0; index$sample31$47 < 3; index$sample31$47 += 1) {
														int distributionTempVariable$cat$49 = index$sample31$47;
														double cv$probabilitySample31Value48 = (1.0 * distribution$sample31[index$sample31$47]);
														{
															{
																if(!(distributionTempVariable$cat$49 == 1)) {
																	int traceTempVariable$result$52_1 = cv$currentValue;
																	{
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$cat$86_1 = distributionTempVariable$cat$26;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$52_1;
																								double var46 = (double)traceTempVariable$cat$86_1;
																								if(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$87_1 = distributionTempVariable$cat$49;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$52_1;
																								double var46 = (double)traceTempVariable$cat$87_1;
																								if(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample31$88 = 0; index$sample31$88 < 3; index$sample31$88 += 1) {
																						int distributionTempVariable$cat$90 = index$sample31$88;
																						double cv$probabilitySample31Value89 = (cv$probabilitySample31Value48 * distribution$sample31[index$sample31$88]);
																						{
																							int traceTempVariable$cat$91_1 = distributionTempVariable$cat$90;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$52_1;
																										double var46 = (double)traceTempVariable$cat$91_1;
																										if(((Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value89);
																									}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$35_1 = 0.3;
							if((1 == cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$35_1, 10));
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$54_1 = cv$currentValue;
													{
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double var47 = (double)traceTempVariable$result$54_1;
																				double var46 = (double)cat;
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$31 = 0; index$sample31$31 < 3; index$sample31$31 += 1) {
							int distributionTempVariable$cat$33 = index$sample31$31;
							double cv$probabilitySample31Value32 = (1.0 * distribution$sample31[index$sample31$31]);
							{
								{
									double traceTempVariable$var40$36_1 = 0.3;
									if((1 == distributionTempVariable$cat$33)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value32);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value32) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$36_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$33 == 1)) {
															int traceTempVariable$result$60_1 = cv$currentValue;
															{
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			int traceTempVariable$cat$93_1 = distributionTempVariable$cat$33;
																			{
																				{
																					{
																						double var47 = (double)traceTempVariable$result$60_1;
																						double var46 = (double)traceTempVariable$cat$93_1;
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample31$94 = 0; index$sample31$94 < 3; index$sample31$94 += 1) {
																				int distributionTempVariable$cat$96 = index$sample31$94;
																				double cv$probabilitySample31Value95 = (1.0 * distribution$sample31[index$sample31$94]);
																				{
																					int traceTempVariable$cat$97_1 = distributionTempVariable$cat$96;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$60_1;
																								double var46 = (double)traceTempVariable$cat$97_1;
																								if(((Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value95);
																							}
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
												if(!true) {
													for(int index$sample31$56 = 0; index$sample31$56 < 3; index$sample31$56 += 1) {
														int distributionTempVariable$cat$58 = index$sample31$56;
														double cv$probabilitySample31Value57 = (1.0 * distribution$sample31[index$sample31$56]);
														{
															{
																if(!(distributionTempVariable$cat$58 == 1)) {
																	int traceTempVariable$result$61_1 = cv$currentValue;
																	{
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$cat$98_1 = distributionTempVariable$cat$33;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$61_1;
																								double var46 = (double)traceTempVariable$cat$98_1;
																								if(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$99_1 = distributionTempVariable$cat$58;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$61_1;
																								double var46 = (double)traceTempVariable$cat$99_1;
																								if(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample31$100 = 0; index$sample31$100 < 3; index$sample31$100 += 1) {
																						int distributionTempVariable$cat$102 = index$sample31$100;
																						double cv$probabilitySample31Value101 = (cv$probabilitySample31Value57 * distribution$sample31[index$sample31$100]);
																						{
																							int traceTempVariable$cat$103_1 = distributionTempVariable$cat$102;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$61_1;
																										double var46 = (double)traceTempVariable$cat$103_1;
																										if(((Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value101);
																									}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$42_1 = 0.5;
							if((2 == cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$42_1, 10));
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$63_1 = cv$currentValue;
													{
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double var47 = (double)traceTempVariable$result$63_1;
																				double var46 = (double)cat;
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$38 = 0; index$sample31$38 < 3; index$sample31$38 += 1) {
							int distributionTempVariable$cat$40 = index$sample31$38;
							double cv$probabilitySample31Value39 = (1.0 * distribution$sample31[index$sample31$38]);
							{
								{
									double traceTempVariable$var40$43_1 = 0.5;
									if((2 == distributionTempVariable$cat$40)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value39);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value39) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$43_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$40 == 1)) {
															int traceTempVariable$result$69_1 = cv$currentValue;
															{
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			int traceTempVariable$cat$105_1 = distributionTempVariable$cat$40;
																			{
																				{
																					{
																						double var47 = (double)traceTempVariable$result$69_1;
																						double var46 = (double)traceTempVariable$cat$105_1;
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample31$106 = 0; index$sample31$106 < 3; index$sample31$106 += 1) {
																				int distributionTempVariable$cat$108 = index$sample31$106;
																				double cv$probabilitySample31Value107 = (1.0 * distribution$sample31[index$sample31$106]);
																				{
																					int traceTempVariable$cat$109_1 = distributionTempVariable$cat$108;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$69_1;
																								double var46 = (double)traceTempVariable$cat$109_1;
																								if(((Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value107);
																							}
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
												if(!true) {
													for(int index$sample31$65 = 0; index$sample31$65 < 3; index$sample31$65 += 1) {
														int distributionTempVariable$cat$67 = index$sample31$65;
														double cv$probabilitySample31Value66 = (1.0 * distribution$sample31[index$sample31$65]);
														{
															{
																if(!(distributionTempVariable$cat$67 == 1)) {
																	int traceTempVariable$result$70_1 = cv$currentValue;
																	{
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$cat$110_1 = distributionTempVariable$cat$40;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$70_1;
																								double var46 = (double)traceTempVariable$cat$110_1;
																								if(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$111_1 = distributionTempVariable$cat$67;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$70_1;
																								double var46 = (double)traceTempVariable$cat$111_1;
																								if(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample31$112 = 0; index$sample31$112 < 3; index$sample31$112 += 1) {
																						int distributionTempVariable$cat$114 = index$sample31$112;
																						double cv$probabilitySample31Value113 = (cv$probabilitySample31Value66 * distribution$sample31[index$sample31$112]);
																						{
																							int traceTempVariable$cat$115_1 = distributionTempVariable$cat$114;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$70_1;
																										double var46 = (double)traceTempVariable$cat$115_1;
																										if(((Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value113);
																									}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
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
			var43 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
			{
				{
					if(!(cat == 1)) {
						{
							result = var43;
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var31$stateProbabilityGlobal = new double[3];
		}
		{
			cv$var43$stateProbabilityGlobal = new double[(10 + 1)];
		}
	}

	@Override
	public final void allocator() {
		{
			bias = new double[3];
		}
		{
			prob = new double[3];
		}
		{
			distribution$sample31 = new double[3];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else {
			if(!fixedFlag$sample31)
				result = 5;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + (double)result);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		double[] cv$distribution$sample31 = distribution$sample31;
		for(int index$var30 = 0; index$var30 < 3; index$var30 += 1) {
			double cv$value = (((0.0 <= index$var30) && (index$var30 < 3))?prob[index$var30]:0.0);
			if(!fixedFlag$sample31)
				cv$distribution$sample31[index$var30] = cv$value;
		}
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + (double)result);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else {
			if(!fixedFlag$sample31)
				result = 5;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				sample31();
			if(!(cat == 1)) {
				if(!fixedFlag$sample45)
					sample45();
			}
		} else {
			if(!(cat == 1)) {
				if(!fixedFlag$sample45)
					sample45();
			}
			if(!fixedFlag$sample31)
				sample31();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		bias[0] = 0.2;
		bias[1] = 0.3;
		bias[2] = 0.5;
		prob[0] = 0.2;
		prob[1] = 0.4;
		prob[2] = 0.4;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$cat = Double.NaN;
		logProbability$var43 = 0.0;
		logProbability$result = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$sample45 = Double.NaN;
		if(!fixedProbFlag$sample51)
			logProbability$data = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void propagateObservedValues() {
		data = observedData;
	}

	@Override
	public final void setIntermediates() {
		if(!(cat == 1)) {
			if((fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}