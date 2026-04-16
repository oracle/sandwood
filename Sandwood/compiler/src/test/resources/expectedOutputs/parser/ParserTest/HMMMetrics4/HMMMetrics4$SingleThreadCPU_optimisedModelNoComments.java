package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics4$SingleThreadCPU extends CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
boolean[][] constrainedFlag$sample134;
	boolean[][] constrainedFlag$sample162;
	boolean[][] constrainedFlag$sample190;
	boolean constrainedFlag$sample20 = true;
	boolean[] constrainedFlag$sample33;
	boolean[] constrainedFlag$sample57;
	boolean[][] constrainedFlag$sample76;
	double[][] current_metric_mean;
	double[][] current_metric_valid_bias;
	double[][] current_metric_var;
	double[][] distribution$sample57;
	double[][][] distribution$sample76;
	boolean fixedFlag$sample134 = false;
	boolean fixedFlag$sample162 = false;
	boolean fixedFlag$sample190 = false;
	boolean fixedFlag$sample20 = false;
	boolean fixedFlag$sample33 = false;
	boolean fixedFlag$sample57 = false;
	boolean fixedFlag$sample76 = false;
	boolean fixedProbFlag$sample134 = false;
	boolean fixedProbFlag$sample162 = false;
	boolean fixedProbFlag$sample190 = false;
	boolean fixedProbFlag$sample20 = false;
	boolean fixedProbFlag$sample241 = false;
	boolean fixedProbFlag$sample256 = false;
	boolean fixedProbFlag$sample33 = false;
	boolean fixedProbFlag$sample57 = false;
	boolean fixedProbFlag$sample76 = false;
	double[] initialStateDistribution;
	int[][] length$metric;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$current_metric_mean;
	double logProbability$current_metric_valid_bias;
	double logProbability$current_metric_var;
	double logProbability$initialStateDistribution;
	double logProbability$m;
	double logProbability$metric_g;
	double logProbability$metric_valid_g;
	double logProbability$metric_valid_inner;
	double[][][] logProbability$sample241;
	double[][][] logProbability$sample256;
	double[] logProbability$sample57;
	double[][] logProbability$sample76;
	double logProbability$st;
	double logProbability$var130;
	double logProbability$var157;
	double logProbability$var184;
	double logProbability$var245;
	double logProbability$var33;
	double[][] m;
	int max_metric;
	double[][][] metric;
	double[][][] metric_g;
	boolean[][][] metric_valid;
	boolean[][][] metric_valid_g;
	int noSamples;
	int noServers;
	int noStates;
	int[][] st;
	boolean system$gibbsForward = true;
	double[] v;
	double[][][] var245;
	double[] cv$distributionAccumulator$var73;
	double[] cv$var20$countGlobal;
	double[] cv$var33$countGlobal;
	double[] cv$var55$stateProbabilityGlobal;
	double[] cv$var74$stateProbabilityGlobal;
	boolean[][][] guard$sample57gaussian255$global;
	boolean[][][] guard$sample76gaussian255$global;

	public HMMMetrics4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$current_metric_mean() {
		return current_metric_mean;
	}

	@Override
	public final void set$current_metric_mean(double[][] cv$value, boolean allocated$) {
		current_metric_mean = cv$value;
		fixedProbFlag$sample134 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value, boolean allocated$) {
		current_metric_valid_bias = cv$value;
		fixedProbFlag$sample190 = false;
		fixedProbFlag$sample241 = false;
	}

	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	@Override
	public final void set$current_metric_var(double[][] cv$value, boolean allocated$) {
		current_metric_var = cv$value;
		fixedProbFlag$sample162 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	@Override
	public final void set$distribution$sample57(double[][] cv$value, boolean allocated$) {
		distribution$sample57 = cv$value;
	}

	@Override
	public final double[][][] get$distribution$sample76() {
		return distribution$sample76;
	}

	@Override
	public final void set$distribution$sample76(double[][][] cv$value, boolean allocated$) {
		distribution$sample76 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample134() {
		return fixedFlag$sample134;
	}

	@Override
	public final void set$fixedFlag$sample134(boolean cv$value, boolean allocated$) {
		fixedFlag$sample134 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
				boolean[] cv$constrainedFlag$sample134$1 = constrainedFlag$sample134[index$constrainedFlag$sample134$1];
				for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
					cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
			}
		}
		fixedProbFlag$sample134 = (cv$value && fixedProbFlag$sample134);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample162() {
		return fixedFlag$sample162;
	}

	@Override
	public final void set$fixedFlag$sample162(boolean cv$value, boolean allocated$) {
		fixedFlag$sample162 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
				boolean[] cv$constrainedFlag$sample162$1 = constrainedFlag$sample162[index$constrainedFlag$sample162$1];
				for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
					cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
			}
		}
		fixedProbFlag$sample162 = (cv$value && fixedProbFlag$sample162);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	@Override
	public final void set$fixedFlag$sample190(boolean cv$value, boolean allocated$) {
		fixedFlag$sample190 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
				boolean[] cv$constrainedFlag$sample190$1 = constrainedFlag$sample190[index$constrainedFlag$sample190$1];
				for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
					cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
			}
		}
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		fixedFlag$sample20 = cv$value;
		constrainedFlag$sample20 = (cv$value || constrainedFlag$sample20);
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value, boolean allocated$) {
		fixedFlag$sample33 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
				constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
		}
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		fixedFlag$sample57 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
				constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		}
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value, boolean allocated$) {
		fixedFlag$sample76 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
				boolean[] cv$constrainedFlag$sample76$1 = constrainedFlag$sample76[index$constrainedFlag$sample76$1];
				for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
					cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
			}
		}
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample57 = false;
	}

	@Override
	public final int[][] get$length$metric() {
		return length$metric;
	}

	@Override
	public final void set$length$metric(int[][] cv$value, boolean allocated$) {
		length$metric = cv$value;
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
	public final double get$logProbability$current_metric_mean() {
		return logProbability$current_metric_mean;
	}

	@Override
	public final double get$logProbability$current_metric_valid_bias() {
		return logProbability$current_metric_valid_bias;
	}

	@Override
	public final double get$logProbability$current_metric_var() {
		return logProbability$current_metric_var;
	}

	@Override
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$metric_g() {
		return logProbability$metric_g;
	}

	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample33 = false;
		fixedProbFlag$sample76 = false;
	}

	@Override
	public final int get$max_metric() {
		return max_metric;
	}

	@Override
	public final void set$max_metric(int cv$value, boolean allocated$) {
		max_metric = cv$value;
	}

	@Override
	public final double[][][] get$metric() {
		return metric;
	}

	@Override
	public final void set$metric(double[][][] cv$value, boolean allocated$) {
		metric = cv$value;
	}

	@Override
	public final double[][][] get$metric_g() {
		return metric_g;
	}

	@Override
	public final boolean[][][] get$metric_valid() {
		return metric_valid;
	}

	@Override
	public final void set$metric_valid(boolean[][][] cv$value, boolean allocated$) {
		metric_valid = cv$value;
	}

	@Override
	public final boolean[][][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	@Override
	public final int get$noServers() {
		return noServers;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
		noStates = cv$value;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample76 = false;
		fixedProbFlag$sample241 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample134(int var119, int var129) {
		current_metric_mean[var119][var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
	}

	private final void drawValueSample162(int var146, int var156) {
		current_metric_var[var146][var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample190(int var173, int var183) {
		current_metric_valid_bias[var173][var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample20() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
	}

	private final void drawValueSample33(int var32) {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
	}

	private final void drawValueSample57(int sample$var45) {
		st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
	}

	private final void drawValueSample76(int sample$var45, int timeStep$var66) {
		st[sample$var45][timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
	}

	private final void inferSample134(int var119, int var129) {
		constrainedFlag$sample134[var119][var129] = false;
		double cv$originalValue = current_metric_mean[var119][var129];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						if((var129 == st[sample$var196][0])) {
							constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							if(((0 <= var156) && (var156 < noStates))) {
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						constrainedFlag$sample134[var119][var129] = true;
						double var243 = current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var129 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < noStates))) {
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							constrainedFlag$sample134[var119][var129] = true;
							double var243 = current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample134[var119][var129]) {
			current_metric_mean[var119][var129] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						if((var129 == st[sample$var196][0])) {
							constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							if(((0 <= var156) && (var156 < noStates))) {
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						constrainedFlag$sample134[var119][var129] = true;
						double var243 = current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var129 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < noStates))) {
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							constrainedFlag$sample134[var119][var129] = true;
							double var243 = current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				current_metric_mean[var119][var129] = cv$originalValue;
		}
	}

	private final void inferSample162(int var146, int var156) {
		constrainedFlag$sample162[var146][var156] = false;
		double cv$originalValue = current_metric_var[var146][var156];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						if((var156 == st[sample$var196][0])) {
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][0];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var156 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample162[var146][var156]) {
			current_metric_var[var146][var156] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						if((var156 == st[sample$var196][0])) {
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][0];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var156 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				current_metric_var[var146][var156] = cv$originalValue;
		}
	}

	private final void inferSample190(int var173, int var183) {
		constrainedFlag$sample190[var173][var183] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			if((0 < length$metric[sample$var196][0])) {
				if(fixedFlag$sample57) {
					if((var183 == st[sample$var196][0])) {
						constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample57Value7 = distribution$sample57[sample$var196][var183];
					constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample57Value7);
					if(metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + cv$probabilitySample57Value7);
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if(fixedFlag$sample76) {
					if((var183 == st[sample$var196][timeStep$var226])) {
						constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample76Value19 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var183];
					constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample76Value19);
					if(metric_valid_g[sample$var196][var173][timeStep$var226])
						cv$sum = (cv$sum + cv$probabilitySample76Value19);
				}
			}
		}
		if(constrainedFlag$sample190[var173][var183])
			current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample20() {
		constrainedFlag$sample20 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				constrainedFlag$sample20 = true;
				cv$var20$countGlobal[st[sample$var45][0]] = (cv$var20$countGlobal[st[sample$var45][0]] + 1.0);
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		if(constrainedFlag$sample20)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution, noStates);
	}

	private final void inferSample33(int var32) {
		constrainedFlag$sample33[var32] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var33$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						if((var32 == st[sample$var45][0])) {
							constrainedFlag$sample33[var32] = true;
							cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + 1.0);
						}
					} else {
						constrainedFlag$sample33[var32] = true;
						cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + distribution$sample57[sample$var45][var32]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
						constrainedFlag$sample33[var32] = true;
						cv$var33$countGlobal[st[sample$var45][timeStep$var66]] = (cv$var33$countGlobal[st[sample$var45][timeStep$var66]] + 1.0);
					}
				}
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						if((var32 == st[sample$var45][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + distribution$sample76[sample$var45][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample57[sample$var45][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int index$timeStep$52 = (timeStep$var66 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample76[sample$var45][(index$timeStep$52 - 1)][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample33[var32])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var33$countGlobal, m[var32], noStates);
	}

	private final void inferSample57(int sample$var45) {
		constrainedFlag$sample57[sample$var45] = false;
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				constrainedFlag$sample57[sample$var45] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] var72 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[sample$var45][1]) && (st[sample$var45][1] < noStates)) && (0.0 <= var72[st[sample$var45][1]])) && (var72[st[sample$var45][1]] <= 1.0))?Math.log(var72[st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((0 < length$metric[sample$var45][0])) {
				for(int server = 0; server < noServers; server += 1) {
					constrainedFlag$sample57[sample$var45] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var230 = current_metric_valid_bias[server][cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][0]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((!fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var55$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample57[sample$var45]) {
			double[] cv$localProbability = distribution$sample57[sample$var45];
			double cv$logSum;
			double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var55$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample76(int sample$var45, int timeStep$var66) {
		constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var66)) {
			if(fixedFlag$sample57) {
				int var32 = st[sample$var45][0];
				if(((0 <= var32) && (var32 < noStates)))
					cv$numStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$numStates = noStates;
			}
		}
		if((0 < noStates)) {
			int index$timeStep$13 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var66)))
				cv$numStates = noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var66)) {
				if(fixedFlag$sample57) {
					int var32 = st[sample$var45][0];
					if(((0 <= var32) && (var32 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var72 = m[st[sample$var45][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var72[cv$valuePos])) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var45][0])) {
							for(int server = 0; server < noServers; server += 1) {
								constrainedFlag$sample76[sample$var45][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									double var230 = current_metric_valid_bias[server][cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var243 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var243 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
						double cv$probabilitySample57Value23 = distribution$sample57[sample$var45][index$sample57$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
						double[] var72 = m[index$sample57$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample$var45][0])) {
							for(int server = 0; server < noServers; server += 1) {
								constrainedFlag$sample76[sample$var45][0] = true;
								double var230 = current_metric_valid_bias[server][cv$valuePos];
								cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var66))) {
				for(int index$sample76$31 = 0; index$sample76$31 < noStates; index$sample76$31 += 1) {
					double cv$probabilitySample76Value32 = distribution$sample76[sample$var45][(index$timeStep$30 - 1)][index$sample76$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
					double[] var72 = m[index$sample76$31];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1) {
						constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
						double var230 = current_metric_valid_bias[server][index$sample76$31];
						cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][timeStep$var66]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var45][server][timeStep$var66])
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var45][server][timeStep$var66])
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					for(int server = 0; server < noServers; server += 1) {
						if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var66 + 1);
			if((index$timeStep$265_3 < length$metric[sample$var45][0])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var66)) {
						if(fixedFlag$sample57) {
							int index$var32$276_1 = st[sample$var45][0];
							if(((0 <= index$var32$276_1) && (index$var32$276_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample57$272 = 0; index$sample57$272 < noStates; index$sample57$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[sample$var45][index$sample57$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var66 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var66)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample76$281 = 0; index$sample76$281 < noStates; index$sample76$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample76[sample$var45][(index$timeStep$280 - 1)][index$sample76$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][(index$timeStep$265_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var74$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)]) {
			double[] cv$localProbability = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$var74$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var74$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample241() {
		if(!fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
						if((0 == timeStep$var226)) {
							if(fixedFlag$sample57) {
								int var183 = st[sample$var196][0];
								if(((0 <= var183) && (var183 < noStates))) {
									double var230 = current_metric_valid_bias[server][st[sample$var196][0]];
									cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
									double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
									double var230 = current_metric_valid_bias[server][index$sample57$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
								}
							}
						}
						if((1 <= timeStep$var226)) {
							if(fixedFlag$sample76) {
								int var183 = st[sample$var196][timeStep$var226];
								if(((0 <= var183) && (var183 < noStates))) {
									double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
									double cv$weightedProbability = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
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
							} else {
								for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
									double cv$probabilitySample76Value14 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$13];
									double var230 = current_metric_valid_bias[server][index$sample76$13];
									double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value14);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample256() {
		if(!fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = var245[sample$var196][server][timeStep$var226];
							if((0 == timeStep$var226)) {
								if(fixedFlag$sample57) {
									if((0 <= st[sample$var196][0])) {
										int var129 = st[sample$var196][0];
										if(((0 <= var129) && (var129 < noStates))) {
											double var243 = current_metric_var[server][st[sample$var196][0]];
											cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][0]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
										double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
										double var243 = current_metric_var[server][index$sample57$4];
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample57$4]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
									}
								}
							}
							if((1 <= timeStep$var226)) {
								if(fixedFlag$sample76) {
									if((0 <= st[sample$var196][timeStep$var226])) {
										int var129 = st[sample$var196][timeStep$var226];
										if(((0 <= var129) && (var129 < noStates))) {
											double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
											double cv$weightedProbability = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
								} else {
									for(int index$sample76$49 = 0; index$sample76$49 < noStates; index$sample76$49 += 1) {
										double cv$probabilitySample76Value50 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$49];
										double var243 = current_metric_var[server][index$sample76$49];
										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample76$49]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value50);
									}
								}
							}
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			logProbability$var245 = (logProbability$var245 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			logProbability$var245 = (logProbability$var245 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					int cv$sampleValue = st[sample$var45][0];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample57[sample$var45] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = fixedFlag$sample20;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample57[sample$var45]);
			if(fixedFlag$sample57)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample76() {
		if(!fixedProbFlag$sample76) {
			if(fixedFlag$sample76) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample$var45][timeStep$var66];
						if((1 == timeStep$var66)) {
							if(fixedFlag$sample57) {
								int var32 = st[sample$var45][0];
								if(((0 <= var32) && (var32 < noStates))) {
									double[] var72 = m[st[sample$var45][0]];
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
									double cv$probabilitySample57Value7 = distribution$sample57[sample$var45][index$sample57$6];
									double[] var72 = m[index$sample57$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value7);
								}
							}
						}
						if((2 <= timeStep$var66)) {
							int var32 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < noStates))) {
								double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample76 = (fixedFlag$sample33 && fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
			}
			if(fixedFlag$sample76)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample134() {
		if(!fixedProbFlag$sample134) {
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					double cv$sampleValue = current_metric_mean[var119][var129];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var130 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample134 = fixedFlag$sample134;
		} else {
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var130);
			logProbability$$model = (logProbability$$model + logProbability$var130);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var130);
		}
	}

	private final void logProbabilityValue$sample162() {
		if(!fixedProbFlag$sample162) {
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var146][var156], 1.0, 1.0));
			}
			logProbability$var157 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample162 = fixedFlag$sample162;
		} else {
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var157);
			logProbability$$model = (logProbability$$model + logProbability$var157);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var157);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var173][var183], 1.0, 1.0));
			}
			logProbability$var184 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample190 = fixedFlag$sample190;
		} else {
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var184);
			logProbability$$model = (logProbability$$model + logProbability$var184);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var184);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample241() {
		if(!fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
						double cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample256() {
		if(!fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
							double cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][server][timeStep$var226] - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			logProbability$var245 = (logProbability$var245 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample256 = (((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			logProbability$var245 = (logProbability$var245 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < noStates; var32 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var32], v, noStates));
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample33 = fixedFlag$sample33;
		} else {
			logProbability$m = (logProbability$m + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				int cv$sampleValue = st[sample$var45][0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample57[sample$var45] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample57[sample$var45]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int cv$sampleValue = st[sample$var45][timeStep$var66];
					double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

<<<<<<< Renaming_functions
	private final void sample134(int var119, int var129) {
		constrainedFlag$sample134[var119][var129] = false;
		double cv$originalValue = current_metric_mean[var119][var129];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						if((var129 == st[sample$var196][0])) {
							constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							if(((0 <= var156) && (var156 < noStates))) {
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						constrainedFlag$sample134[var119][var129] = true;
						double var243 = current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var129 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < noStates))) {
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							constrainedFlag$sample134[var119][var129] = true;
							double var243 = current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample134[var119][var129]) {
			current_metric_mean[var119][var129] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var119][0])) {
					if(fixedFlag$sample57) {
						if((var129 == st[sample$var196][0])) {
							constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							if(((0 <= var156) && (var156 < noStates))) {
								double var243 = current_metric_var[var119][st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var129];
						constrainedFlag$sample134[var119][var129] = true;
						double var243 = current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var129 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < noStates))) {
									double var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							constrainedFlag$sample134[var119][var129] = true;
							double var243 = current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				current_metric_mean[var119][var129] = cv$originalValue;
		}
	}

	private final void sample162(int var146, int var156) {
		constrainedFlag$sample162[var146][var156] = false;
		double cv$originalValue = current_metric_var[var146][var156];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						if((var156 == st[sample$var196][0])) {
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][0];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var156 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample162[var146][var156]) {
			current_metric_var[var146][var156] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if(((0 < length$metric[sample$var196][0]) && metric_valid_g[sample$var196][var146][0])) {
					if(fixedFlag$sample57) {
						if((var156 == st[sample$var196][0])) {
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][0];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][var156];
						constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][0] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(fixedFlag$sample76) {
							if((var156 == st[sample$var196][timeStep$var226])) {
								constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				current_metric_var[var146][var156] = cv$originalValue;
		}
	}

	private final void sample190(int var173, int var183) {
		constrainedFlag$sample190[var173][var183] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			if((0 < length$metric[sample$var196][0])) {
				if(fixedFlag$sample57) {
					if((var183 == st[sample$var196][0])) {
						constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample57Value7 = distribution$sample57[sample$var196][var183];
					constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample57Value7);
					if(metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + cv$probabilitySample57Value7);
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if(fixedFlag$sample76) {
					if((var183 == st[sample$var196][timeStep$var226])) {
						constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample76Value19 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][var183];
					constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample76Value19);
					if(metric_valid_g[sample$var196][var173][timeStep$var226])
						cv$sum = (cv$sum + cv$probabilitySample76Value19);
				}
			}
		}
		if(constrainedFlag$sample190[var173][var183])
			current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample20() {
		constrainedFlag$sample20 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				constrainedFlag$sample20 = true;
				cv$var20$countGlobal[st[sample$var45][0]] = (cv$var20$countGlobal[st[sample$var45][0]] + 1.0);
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		if(constrainedFlag$sample20)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution, noStates);
	}

	private final void sample33(int var32) {
		constrainedFlag$sample33[var32] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var33$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						if((var32 == st[sample$var45][0])) {
							constrainedFlag$sample33[var32] = true;
							cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + 1.0);
						}
					} else {
						constrainedFlag$sample33[var32] = true;
						cv$var33$countGlobal[st[sample$var45][1]] = (cv$var33$countGlobal[st[sample$var45][1]] + distribution$sample57[sample$var45][var32]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
						constrainedFlag$sample33[var32] = true;
						cv$var33$countGlobal[st[sample$var45][timeStep$var66]] = (cv$var33$countGlobal[st[sample$var45][timeStep$var66]] + 1.0);
					}
				}
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if((1 < length$metric[sample$var45][0])) {
					if(fixedFlag$sample57) {
						if((var32 == st[sample$var45][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + distribution$sample76[sample$var45][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample57[sample$var45][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int index$timeStep$52 = (timeStep$var66 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample76[sample$var45][(index$timeStep$52 - 1)][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var33$countGlobal[cv$loopIndex] = (cv$var33$countGlobal[cv$loopIndex] + (distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample33[var32])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var33$countGlobal, m[var32], noStates);
	}

	private final void sample57(int sample$var45) {
		constrainedFlag$sample57[sample$var45] = false;
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				constrainedFlag$sample57[sample$var45] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] var72 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[sample$var45][1]) && (st[sample$var45][1] < noStates)) && (0.0 <= var72[st[sample$var45][1]])) && (var72[st[sample$var45][1]] <= 1.0))?Math.log(var72[st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((0 < length$metric[sample$var45][0])) {
				for(int server = 0; server < noServers; server += 1) {
					constrainedFlag$sample57[sample$var45] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var230 = current_metric_valid_bias[server][cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][0]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255$global[sample$var45][server][0])) {
						guard$sample57gaussian255$global[sample$var45][server][0] = true;
						constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((!fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var55$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample57[sample$var45]) {
			double[] cv$localProbability = distribution$sample57[sample$var45];
			double cv$logSum;
			double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var55$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample76(int sample$var45, int timeStep$var66) {
		constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var66)) {
			if(fixedFlag$sample57) {
				int var32 = st[sample$var45][0];
				if(((0 <= var32) && (var32 < noStates)))
					cv$numStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$numStates = noStates;
			}
		}
		if((0 < noStates)) {
			int index$timeStep$13 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var66)))
				cv$numStates = noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var66)) {
				if(fixedFlag$sample57) {
					int var32 = st[sample$var45][0];
					if(((0 <= var32) && (var32 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var72 = m[st[sample$var45][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var72[cv$valuePos])) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var45][0])) {
							for(int server = 0; server < noServers; server += 1) {
								constrainedFlag$sample76[sample$var45][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									double var230 = current_metric_valid_bias[server][cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var243 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var243 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
						double cv$probabilitySample57Value23 = distribution$sample57[sample$var45][index$sample57$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
						double[] var72 = m[index$sample57$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample$var45][0])) {
							for(int server = 0; server < noServers; server += 1) {
								constrainedFlag$sample76[sample$var45][0] = true;
								double var230 = current_metric_valid_bias[server][cv$valuePos];
								cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255$global[sample$var45][server][1])) {
									guard$sample76gaussian255$global[sample$var45][server][1] = true;
									constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var66))) {
				for(int index$sample76$31 = 0; index$sample76$31 < noStates; index$sample76$31 += 1) {
					double cv$probabilitySample76Value32 = distribution$sample76[sample$var45][(index$timeStep$30 - 1)][index$sample76$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
					double[] var72 = m[index$sample76$31];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1) {
						constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
						double var230 = current_metric_valid_bias[server][index$sample76$31];
						cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((metric_valid_g[sample$var45][server][timeStep$var66]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var45][server][timeStep$var66])
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var45][server][timeStep$var66])
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					for(int server = 0; server < noServers; server += 1) {
						if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((var245[sample$var45][server][timeStep$var66] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var66 + 1);
			if((index$timeStep$265_3 < length$metric[sample$var45][0])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var66)) {
						if(fixedFlag$sample57) {
							int index$var32$276_1 = st[sample$var45][0];
							if(((0 <= index$var32$276_1) && (index$var32$276_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample57$272 = 0; index$sample57$272 < noStates; index$sample57$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[sample$var45][index$sample57$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var66 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var66)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample76$281 = 0; index$sample76$281 < noStates; index$sample76$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample76[sample$var45][(index$timeStep$280 - 1)][index$sample76$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var73, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][(index$timeStep$265_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var74$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)]) {
			double[] cv$localProbability = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$var74$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var74$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

=======
>>>>>>> 36e6d3b Restructuring naming and renaming functions, this does result in them changing location in the generated source. This is done to allow the addition of unconstrained variables to be completed by adding in the methods to sample the unconstrained values.
	@Override
	public final void allocate() {
		v = new double[noStates];
		if(!fixedFlag$sample20)
			initialStateDistribution = new double[noStates];
		if(!fixedFlag$sample33) {
			m = new double[noStates][];
			for(int var32 = 0; var32 < noStates; var32 += 1)
				m[var32] = new double[noStates];
		}
		if((!fixedFlag$sample57 || !fixedFlag$sample76)) {
			st = new int[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				st[sample$var45] = new int[length$metric[sample$var45][0]];
		}
		metric_g = new double[length$metric.length][][];
		for(int var90 = 0; var90 < length$metric.length; var90 += 1)
			metric_g[var90] = new double[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_g[sample$var196][server] = new double[length$metric[sample$var196][0]];
		}
		metric_valid_g = new boolean[length$metric.length][][];
		for(int var103 = 0; var103 < length$metric.length; var103 += 1)
			metric_valid_g[var103] = new boolean[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_valid_g[sample$var196][server] = new boolean[length$metric[sample$var196][0]];
		}
		if(!fixedFlag$sample134) {
			current_metric_mean = new double[length$metric[0].length][];
			for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
				current_metric_mean[var119] = new double[noStates];
		}
		if(!fixedFlag$sample162) {
			current_metric_var = new double[length$metric[0].length][];
			for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
				current_metric_var[var146] = new double[noStates];
		}
		if(!fixedFlag$sample190) {
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
				current_metric_valid_bias[var173] = new double[noStates];
		}
		var245 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			var245[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		distribution$sample76 = new double[length$metric.length][][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var45][0] - 1)][];
			distribution$sample76[sample$var45] = subarray$0;
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				subarray$0[(timeStep$var66 - 1)] = new double[noStates];
		}
		distribution$sample57 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			distribution$sample57[sample$var45] = new double[noStates];
		constrainedFlag$sample190 = new boolean[length$metric[0].length][];
		for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
			constrainedFlag$sample190[var173] = new boolean[noStates];
		constrainedFlag$sample76 = new boolean[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			constrainedFlag$sample76[sample$var45] = new boolean[(length$metric[sample$var45][0] - 1)];
		constrainedFlag$sample57 = new boolean[length$metric.length];
		constrainedFlag$sample134 = new boolean[length$metric[0].length][];
		for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
			constrainedFlag$sample134[var119] = new boolean[noStates];
		constrainedFlag$sample162 = new boolean[length$metric[0].length][];
		for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
			constrainedFlag$sample162[var146] = new boolean[noStates];
		constrainedFlag$sample33 = new boolean[noStates];
		logProbability$sample57 = new double[length$metric.length];
		logProbability$sample76 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			logProbability$sample76[sample$var45] = new double[(length$metric[sample$var45][0] - 1)];
		logProbability$sample241 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample241[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		logProbability$sample256 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample256[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		cv$var20$countGlobal = new double[noStates];
		cv$var33$countGlobal = new double[noStates];
		cv$distributionAccumulator$var73 = new double[noStates];
		cv$var55$stateProbabilityGlobal = new double[noStates];
		{
			int cv$max_server = 0;
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			guard$sample57gaussian255$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var226];
		}
		cv$var74$stateProbabilityGlobal = new double[noStates];
		int cv$max_server = 0;
		int cv$max_timeStep$var226 = 0;
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		guard$sample76gaussian255$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var226];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			double[][] var211 = metric_g[sample$var196];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57) {
				double[] cv$distribution$sample57 = distribution$sample57[sample$var45];
				for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1)
					cv$distribution$sample57[index$var54] = (((0.0 <= initialStateDistribution[index$var54]) && (initialStateDistribution[index$var54] <= 1.0))?initialStateDistribution[index$var54]:0.0);
			}
			if(!fixedFlag$sample76) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double[] cv$distribution$sample76 = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						cv$distribution$sample76[index$var73] = 0.0;
					if((1 == timeStep$var66)) {
						if(fixedFlag$sample57) {
							int var32 = st[sample$var45][0];
							if(((0 <= var32) && (var32 < noStates))) {
								double[] var72 = m[st[sample$var45][0]];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0));
							}
						} else {
							for(int index$sample57$3 = 0; index$sample57$3 < noStates; index$sample57$3 += 1) {
								double cv$probabilitySample57Value4 = distribution$sample57[sample$var45][index$sample57$3];
								double[] var72 = m[index$sample57$3];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var66 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample76$12 = 0; index$sample76$12 < noStates; index$sample76$12 += 1) {
							double cv$probabilitySample76Value13 = distribution$sample76[sample$var45][(index$timeStep$11 - 1)][index$sample76$12];
							double[] var72 = m[index$sample76$12];
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
								cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
						}
					}
					double cv$var73$sum = 0.0;
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
					for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			double[][] var211 = metric_g[sample$var196];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33) {
			for(int var32 = 0; var32 < noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!fixedFlag$sample57)
				st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			if(!fixedFlag$sample76) {
				int[] var67 = st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		if(!fixedFlag$sample134) {
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				double[] var120 = current_metric_mean[var119];
				for(int var129 = 0; var129 < noStates; var129 += 1)
					var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample162) {
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				double[] var147 = current_metric_var[var146];
				for(int var156 = 0; var156 < noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample190) {
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				double[] var174 = current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				inferSample20();
			if(!fixedFlag$sample33) {
				for(int var32 = 0; var32 < noStates; var32 += 1)
					inferSample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if(!fixedFlag$sample57)
					inferSample57(sample$var45);
				if(!fixedFlag$sample76) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
						inferSample76(sample$var45, timeStep$var66);
				}
			}
			if(!fixedFlag$sample134) {
				for(int var119 = 0; var119 < noServers; var119 += 1) {
					for(int var129 = 0; var129 < noStates; var129 += 1)
						inferSample134(var119, var129);
				}
			}
			if(!fixedFlag$sample162) {
				for(int var146 = 0; var146 < noServers; var146 += 1) {
					for(int var156 = 0; var156 < noStates; var156 += 1)
						inferSample162(var146, var156);
				}
			}
			if(!fixedFlag$sample190) {
				for(int var173 = 0; var173 < noServers; var173 += 1) {
					for(int var183 = 0; var183 < noStates; var183 += 1)
						inferSample190(var173, var183);
				}
			}
		} else {
			if(!fixedFlag$sample190) {
				for(int var173 = (noServers - 1); var173 >= 0; var173 -= 1) {
					for(int var183 = (noStates - 1); var183 >= 0; var183 -= 1)
						inferSample190(var173, var183);
				}
			}
			if(!fixedFlag$sample162) {
				for(int var146 = (noServers - 1); var146 >= 0; var146 -= 1) {
					for(int var156 = (noStates - 1); var156 >= 0; var156 -= 1)
						inferSample162(var146, var156);
				}
			}
			if(!fixedFlag$sample134) {
				for(int var119 = (noServers - 1); var119 >= 0; var119 -= 1) {
					for(int var129 = (noStates - 1); var129 >= 0; var129 -= 1)
						inferSample134(var119, var129);
				}
			}
			for(int sample$var45 = (noSamples - 1); sample$var45 >= 0; sample$var45 -= 1) {
				if(!fixedFlag$sample76) {
					for(int timeStep$var66 = (length$metric[sample$var45][0] - 1); timeStep$var66 >= 1; timeStep$var66 -= 1)
						inferSample76(sample$var45, timeStep$var66);
				}
				if(!fixedFlag$sample57)
					inferSample57(sample$var45);
			}
			if(!fixedFlag$sample33) {
				for(int var32 = (noStates - 1); var32 >= 0; var32 -= 1)
					inferSample33(var32);
			}
			if(!fixedFlag$sample20)
				inferSample20();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample20)
			drawValueSample20();
		for(int var32 = 0; var32 < noStates; var32 += 1) {
			if(!constrainedFlag$sample33[var32])
				drawValueSample33(var32);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			if(!constrainedFlag$sample57[sample$var45])
				drawValueSample57(sample$var45);
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)])
					drawValueSample76(sample$var45, timeStep$var66);
			}
		}
		for(int var119 = 0; var119 < noServers; var119 += 1) {
			for(int var129 = 0; var129 < noStates; var129 += 1) {
				if(!constrainedFlag$sample134[var119][var129])
					drawValueSample134(var119, var129);
			}
		}
		for(int var146 = 0; var146 < noServers; var146 += 1) {
			for(int var156 = 0; var156 < noStates; var156 += 1) {
				if(!constrainedFlag$sample162[var146][var156])
					drawValueSample162(var146, var156);
			}
		}
		for(int var173 = 0; var173 < noServers; var173 += 1) {
			for(int var183 = 0; var183 < noStates; var183 += 1) {
				if(!constrainedFlag$sample190[var173][var183])
					drawValueSample190(var173, var183);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var33 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				logProbability$sample57[sample$var45] = Double.NaN;
		}
		if(!fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = Double.NaN;
			}
		}
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample134)
			logProbability$var130 = Double.NaN;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample162)
			logProbability$var157 = Double.NaN;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var184 = Double.NaN;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample241[sample$var196][server][timeStep$var226] = Double.NaN;
				}
			}
		}
		logProbability$var245 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample256[sample$var196][server][timeStep$var226] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		noSamples = length$metric.length;
		for(int var16 = 0; var16 < noStates; var16 += 1)
			v[var16] = 0.1;
		noServers = length$metric[0].length;
		for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
			boolean[] cv$constrainedFlag$sample190$1 = constrainedFlag$sample190[index$constrainedFlag$sample190$1];
			for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
				cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
		}
		for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
			boolean[] cv$constrainedFlag$sample76$1 = constrainedFlag$sample76[index$constrainedFlag$sample76$1];
			for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
				cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
		}
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
			boolean[] cv$constrainedFlag$sample134$1 = constrainedFlag$sample134[index$constrainedFlag$sample134$1];
			for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
				cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
		}
		for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
			boolean[] cv$constrainedFlag$sample162$1 = constrainedFlag$sample162[index$constrainedFlag$sample162$1];
			for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
				cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
		}
		for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
			constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(fixedFlag$sample190)
			logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityDistribution$sample241();
		logProbabilityDistribution$sample256();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int cv$length1 = metric_valid_g.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = metric_valid[cv$index1];
				boolean[][] cv$target2 = metric_valid_g[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
					boolean[] cv$source3 = cv$source2[cv$index2];
					boolean[] cv$target3 = cv$target2[cv$index2];
					int cv$length3 = cv$target3.length;
					for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
						cv$target3[cv$index3] = cv$source3[cv$index3];
				}
			}
		}
		int cv$length1 = metric_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[][] cv$source2 = metric[cv$index1];
			double[][] cv$target2 = metric_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				double[] cv$source3 = cv$source2[cv$index2];
				double[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
		for(int sample$var196 = (noSamples - 1); sample$var196 >= 0; sample$var196 -= 1) {
			for(int server = (noServers - 1); server >= 0; server -= 1) {
				for(int timeStep$var226 = (length$metric[sample$var196][0] - 1); timeStep$var226 >= 0; timeStep$var226 -= 1) {
					if(metric_valid_g[sample$var196][server][timeStep$var226])
						var245[sample$var196][server][timeStep$var226] = metric_g[sample$var196][server][timeStep$var226];
				}
			}
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics4(\n"
		     + "               double[][][] metric,\n"
		     + "               boolean[][][] metric_valid, \n"
		     + "               int max_metric,\n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    //Calculate all the state transitions\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Calculate the number of servers\n"
		     + "    int noServers = metric[0].length;    \n"
		     + "    \n"
		     + "    // Allocate space for each generated metric.    \n"
		     + "    double[][][] metric_g = new double[noSamples][noServers][];\n"
		     + "    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n"
		     + "\n"
		     + "    // Calculate metric parameters\n"
		     + "    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        for(int server = 0; server < noServers; server++) {\n"
		     + "            //Allocate space for the time series\n"
		     + "            double[] metric_inner = new double[streamLength];\n"
		     + "            metric_g[sample][server] = metric_inner;\n"
		     + "            \n"
		     + "            boolean[] metric_valid_inner = new boolean[streamLength];\n"
		     + "            metric_valid_g[sample][server] = metric_valid_inner;\n"
		     + "            \n"
		     + "            //Generate values.\n"
		     + "            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "                int currentState = st[sample][timeStep];\n"
		     + "                \n"
		     + "                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n"
		     + "                if(metric_valid_inner[timeStep])\n"
		     + "                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "    metric_g.observe(metric);\n"
		     + "}";
	}
}