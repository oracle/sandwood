package org.sandwood.compiler.tests.parser;

import java.util.HashMap;
import java.util.Map;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.CoreModelBase;
import org.sandwood.runtime.internal.model.state.CoreModelState;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.*;

/**
 * Class representing the Sandwood model HMMMetrics4 This is the class that all user
 * interactions with the model should occur through.
 */
public final class HMMMetrics4 extends Model<HMMMetrics4.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
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

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			v = new double[noStates];
			
			// If initialStateDistribution has not been set already allocate space.
			if(!fixedFlag$sample20)
				// Constructor for initialStateDistribution
				initialStateDistribution = new double[noStates];
			
			// If m has not been set already allocate space.
			if(!fixedFlag$sample33) {
				// Constructor for m
				m = new double[noStates][];
				for(int var32 = 0; var32 < noStates; var32 += 1)
					m[var32] = new double[noStates];
			}
			
			// If st has not been set already allocate space.
			if((!fixedFlag$sample57 || !fixedFlag$sample76)) {
				// Constructor for st
				st = new int[length$metric.length][];
				for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
					st[sample$var45] = new int[length$metric[sample$var45][0]];
			}
			
			// Constructor for metric_g
			metric_g = new double[length$metric.length][][];
			for(int var90 = 0; var90 < length$metric.length; var90 += 1)
				metric_g[var90] = new double[length$metric[0].length][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_g[sample$var196][server] = new double[length$metric[sample$var196][0]];
			}
			
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var103 = 0; var103 < length$metric.length; var103 += 1)
				metric_valid_g[var103] = new boolean[length$metric[0].length][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_valid_g[sample$var196][server] = new boolean[length$metric[sample$var196][0]];
			}
			
			// If current_metric_mean has not been set already allocate space.
			if(!fixedFlag$sample134) {
				// Constructor for current_metric_mean
				current_metric_mean = new double[length$metric[0].length][];
				for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
					current_metric_mean[var119] = new double[noStates];
			}
			
			// If current_metric_var has not been set already allocate space.
			if(!fixedFlag$sample162) {
				// Constructor for current_metric_var
				current_metric_var = new double[length$metric[0].length][];
				for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
					current_metric_var[var146] = new double[noStates];
			}
			
			// If current_metric_valid_bias has not been set already allocate space.
			if(!fixedFlag$sample190) {
				// Constructor for current_metric_valid_bias
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
					current_metric_valid_bias[var173] = new double[noStates];
			}
			
			// Constructor for var245
			var245 = new double[length$metric.length][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[length$metric[0].length][];
				var245[sample$var196] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[server] = new double[length$metric[sample$var196][0]];
			}
			
			// Constructor for distribution$sample76
			distribution$sample76 = new double[length$metric.length][][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
				double[][] subarray$0 = new double[(length$metric[sample$var45][0] - 1)][];
				distribution$sample76[sample$var45] = subarray$0;
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					subarray$0[(timeStep$var66 - 1)] = new double[noStates];
			}
			
			// Constructor for distribution$sample57
			distribution$sample57 = new double[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				distribution$sample57[sample$var45] = new double[noStates];
			
			// Constructor for constrainedFlag$sample190
			constrainedFlag$sample190 = new boolean[length$metric[0].length][];
			for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
				constrainedFlag$sample190[var173] = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample76
			constrainedFlag$sample76 = new boolean[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				constrainedFlag$sample76[sample$var45] = new boolean[(length$metric[sample$var45][0] - 1)];
			
			// Constructor for constrainedFlag$sample57
			constrainedFlag$sample57 = new boolean[length$metric.length];
			
			// Constructor for constrainedFlag$sample134
			constrainedFlag$sample134 = new boolean[length$metric[0].length][];
			for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
				constrainedFlag$sample134[var119] = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample162
			constrainedFlag$sample162 = new boolean[length$metric[0].length][];
			for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
				constrainedFlag$sample162[var146] = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample33
			constrainedFlag$sample33 = new boolean[noStates];
			
			// Constructor for logProbability$sample57
			logProbability$sample57 = new double[length$metric.length];
			
			// Constructor for logProbability$sample76
			logProbability$sample76 = new double[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				logProbability$sample76[sample$var45] = new double[(length$metric[sample$var45][0] - 1)];
			
			// Constructor for logProbability$sample241
			logProbability$sample241 = new double[length$metric.length][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[length$metric[0].length][];
				logProbability$sample241[sample$var196] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[server] = new double[length$metric[sample$var196][0]];
			}
			
			// Constructor for logProbability$sample256
			logProbability$sample256 = new double[length$metric.length][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[length$metric[0].length][];
				logProbability$sample256[sample$var196] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[server] = new double[length$metric[sample$var196][0]];
			}
		}

		// Getter for current_metric_mean.
		final double[][] get$current_metric_mean() {
			return current_metric_mean;
		}

		// Setter for current_metric_mean.
		final void set$current_metric_mean(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of current_metric_mean including if probabilities
			// need to be updated.
			current_metric_mean = cv$value;
			
			// Unset the fixed probability flag for sample 134 as it depends on current_metric_mean.
			fixedProbFlag$sample134 = false;
			
			// Unset the fixed probability flag for sample 256 as it depends on current_metric_mean.
			fixedProbFlag$sample256 = false;
		}

		// Getter for current_metric_valid_bias.
		final double[][] get$current_metric_valid_bias() {
			return current_metric_valid_bias;
		}

		// Setter for current_metric_valid_bias.
		final void set$current_metric_valid_bias(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of current_metric_valid_bias including if probabilities
			// need to be updated.
			current_metric_valid_bias = cv$value;
			
			// Unset the fixed probability flag for sample 190 as it depends on current_metric_valid_bias.
			fixedProbFlag$sample190 = false;
			
			// Unset the fixed probability flag for sample 241 as it depends on current_metric_valid_bias.
			fixedProbFlag$sample241 = false;
		}

		// Getter for current_metric_var.
		final double[][] get$current_metric_var() {
			return current_metric_var;
		}

		// Setter for current_metric_var.
		final void set$current_metric_var(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of current_metric_var including if probabilities
			// need to be updated.
			current_metric_var = cv$value;
			
			// Unset the fixed probability flag for sample 162 as it depends on current_metric_var.
			fixedProbFlag$sample162 = false;
			
			// Unset the fixed probability flag for sample 256 as it depends on current_metric_var.
			fixedProbFlag$sample256 = false;
		}

		// Getter for distribution$sample57.
		final double[][] get$distribution$sample57() {
			return distribution$sample57;
		}

		// Setter for distribution$sample57.
		final void set$distribution$sample57(double[][] cv$value, boolean allocated$) {
			distribution$sample57 = cv$value;
		}

		// Getter for distribution$sample76.
		final double[][][] get$distribution$sample76() {
			return distribution$sample76;
		}

		// Setter for distribution$sample76.
		final void set$distribution$sample76(double[][][] cv$value, boolean allocated$) {
			distribution$sample76 = cv$value;
		}

		// Getter for fixedFlag$sample134.
		final boolean get$fixedFlag$sample134() {
			return fixedFlag$sample134;
		}

		// Setter for fixedFlag$sample134.
		final void set$fixedFlag$sample134(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample134 including if probabilities
			// need to be updated.
			fixedFlag$sample134 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
					boolean[] cv$constrainedFlag$sample134$1 = constrainedFlag$sample134[index$constrainedFlag$sample134$1];
					for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
						cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
				}
			}
			
			// Should the probability of sample 134 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample134" with its value "cv$value".
			fixedProbFlag$sample134 = (cv$value && fixedProbFlag$sample134);
			
			// Should the probability of sample 256 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample134" with its value "cv$value".
			fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
		}

		// Getter for fixedFlag$sample162.
		final boolean get$fixedFlag$sample162() {
			return fixedFlag$sample162;
		}

		// Setter for fixedFlag$sample162.
		final void set$fixedFlag$sample162(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample162 including if probabilities
			// need to be updated.
			fixedFlag$sample162 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
					boolean[] cv$constrainedFlag$sample162$1 = constrainedFlag$sample162[index$constrainedFlag$sample162$1];
					for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
						cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
				}
			}
			
			// Should the probability of sample 162 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample162" with its value "cv$value".
			fixedProbFlag$sample162 = (cv$value && fixedProbFlag$sample162);
			
			// Should the probability of sample 256 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample162" with its value "cv$value".
			fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
		}

		// Getter for fixedFlag$sample190.
		final boolean get$fixedFlag$sample190() {
			return fixedFlag$sample190;
		}

		// Setter for fixedFlag$sample190.
		final void set$fixedFlag$sample190(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample190 including if probabilities
			// need to be updated.
			fixedFlag$sample190 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
					boolean[] cv$constrainedFlag$sample190$1 = constrainedFlag$sample190[index$constrainedFlag$sample190$1];
					for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
						cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
				}
			}
			
			// Should the probability of sample 190 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample190" with its value "cv$value".
			fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
			
			// Should the probability of sample 241 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample190" with its value "cv$value".
			fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		}

		// Getter for fixedFlag$sample20.
		final boolean get$fixedFlag$sample20() {
			return fixedFlag$sample20;
		}

		// Setter for fixedFlag$sample20.
		final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
			// need to be updated.
			fixedFlag$sample20 = cv$value;
			
			// Substituted "fixedFlag$sample20" with its value "cv$value".
			constrainedFlag$sample20 = (cv$value || constrainedFlag$sample20);
			
			// Should the probability of sample 20 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample20" with its value "cv$value".
			fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
			
			// Should the probability of sample 57 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample20" with its value "cv$value".
			fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		}

		// Getter for fixedFlag$sample33.
		final boolean get$fixedFlag$sample33() {
			return fixedFlag$sample33;
		}

		// Setter for fixedFlag$sample33.
		final void set$fixedFlag$sample33(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample33 including if probabilities
			// need to be updated.
			fixedFlag$sample33 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
					constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
			}
			
			// Should the probability of sample 33 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample33" with its value "cv$value".
			fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
			
			// Should the probability of sample 76 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample33" with its value "cv$value".
			fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		}

		// Getter for fixedFlag$sample57.
		final boolean get$fixedFlag$sample57() {
			return fixedFlag$sample57;
		}

		// Setter for fixedFlag$sample57.
		final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
			// need to be updated.
			fixedFlag$sample57 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
					constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
			}
			
			// Should the probability of sample 57 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
			
			// Should the probability of sample 76 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
			
			// Should the probability of sample 241 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
			
			// Should the probability of sample 256 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
		}

		// Getter for fixedFlag$sample76.
		final boolean get$fixedFlag$sample76() {
			return fixedFlag$sample76;
		}

		// Setter for fixedFlag$sample76.
		final void set$fixedFlag$sample76(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample76 including if probabilities
			// need to be updated.
			fixedFlag$sample76 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
					boolean[] cv$constrainedFlag$sample76$1 = constrainedFlag$sample76[index$constrainedFlag$sample76$1];
					for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
						cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
				}
			}
			
			// Should the probability of sample 76 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample76" with its value "cv$value".
			fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
			
			// Should the probability of sample 241 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample76" with its value "cv$value".
			fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
			
			// Should the probability of sample 256 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample76" with its value "cv$value".
			fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
		}

		// Getter for initialStateDistribution.
		final double[] get$initialStateDistribution() {
			return initialStateDistribution;
		}

		// Setter for initialStateDistribution.
		final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of initialStateDistribution including if probabilities
			// need to be updated.
			initialStateDistribution = cv$value;
			
			// Unset the fixed probability flag for sample 20 as it depends on initialStateDistribution.
			fixedProbFlag$sample20 = false;
			
			// Unset the fixed probability flag for sample 57 as it depends on initialStateDistribution.
			fixedProbFlag$sample57 = false;
		}

		// Getter for length$metric.
		final int[][] get$length$metric() {
			return length$metric;
		}

		// Setter for length$metric.
		final void set$length$metric(int[][] cv$value, boolean allocated$) {
			length$metric = cv$value;
		}

		// Getter for logProbability$$evidence.
		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		// Getter for the probability of logProbability$$model.
		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		// Getter for logProbability$current_metric_mean.
		final double get$logProbability$current_metric_mean() {
			return logProbability$current_metric_mean;
		}

		// Getter for logProbability$current_metric_valid_bias.
		final double get$logProbability$current_metric_valid_bias() {
			return logProbability$current_metric_valid_bias;
		}

		// Getter for logProbability$current_metric_var.
		final double get$logProbability$current_metric_var() {
			return logProbability$current_metric_var;
		}

		// Getter for logProbability$initialStateDistribution.
		final double get$logProbability$initialStateDistribution() {
			return logProbability$initialStateDistribution;
		}

		// Getter for logProbability$m.
		final double get$logProbability$m() {
			return logProbability$m;
		}

		// Getter for logProbability$metric_g.
		final double get$logProbability$metric_g() {
			return logProbability$metric_g;
		}

		// Getter for logProbability$metric_valid_g.
		final double get$logProbability$metric_valid_g() {
			return logProbability$metric_valid_g;
		}

		// Getter for logProbability$st.
		final double get$logProbability$st() {
			return logProbability$st;
		}

		// Getter for m.
		final double[][] get$m() {
			return m;
		}

		// Setter for m.
		final void set$m(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of m including if probabilities need to be updated.
			m = cv$value;
			
			// Unset the fixed probability flag for sample 33 as it depends on m.
			fixedProbFlag$sample33 = false;
			
			// Unset the fixed probability flag for sample 76 as it depends on m.
			fixedProbFlag$sample76 = false;
		}

		// Getter for max_metric.
		final int get$max_metric() {
			return max_metric;
		}

		// Setter for max_metric.
		final void set$max_metric(int cv$value, boolean allocated$) {
			max_metric = cv$value;
		}

		// Getter for metric.
		final double[][][] get$metric() {
			return metric;
		}

		// Setter for metric.
		final void set$metric(double[][][] cv$value, boolean allocated$) {
			metric = cv$value;
		}

		// Getter for metric_g.
		final double[][][] get$metric_g() {
			return metric_g;
		}

		// Getter for metric_valid.
		final boolean[][][] get$metric_valid() {
			return metric_valid;
		}

		// Setter for metric_valid.
		final void set$metric_valid(boolean[][][] cv$value, boolean allocated$) {
			metric_valid = cv$value;
		}

		// Getter for metric_valid_g.
		final boolean[][][] get$metric_valid_g() {
			return metric_valid_g;
		}

		// Getter for noSamples.
		final int get$noSamples() {
			return noSamples;
		}

		// Getter for noServers.
		final int get$noServers() {
			return noServers;
		}

		// Getter for noStates.
		final int get$noStates() {
			return noStates;
		}

		// Setter for noStates.
		final void set$noStates(int cv$value, boolean allocated$) {
			noStates = cv$value;
		}

		// Getter for st.
		final int[][] get$st() {
			return st;
		}

		// Setter for st.
		final void set$st(int[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of st including if probabilities need to be
			// updated.
			st = cv$value;
			
			// Unset the fixed probability flag for sample 57 as it depends on st.
			fixedProbFlag$sample57 = false;
			
			// Unset the fixed probability flag for sample 76 as it depends on st.
			fixedProbFlag$sample76 = false;
			
			// Unset the fixed probability flag for sample 241 as it depends on st.
			fixedProbFlag$sample241 = false;
			
			// Unset the fixed probability flag for sample 256 as it depends on st.
			fixedProbFlag$sample256 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}
	}

    private final ComputedObjectArrayInternal<double[]> $current_metric_mean = new ComputedObjectArrayInternal<double[]>(this, "current_metric_mean", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$current_metric_mean(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$current_metric_mean(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$current_metric_mean(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample134(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample134())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing current_metric_mean of type double[][] from the
	 * Sandwood model.
	 */
    public final ComputedObjectArray<double[]> current_metric_mean = $current_metric_mean;

    private final ComputedObjectArrayInternal<double[]> $current_metric_valid_bias = new ComputedObjectArrayInternal<double[]>(this, "current_metric_valid_bias", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$current_metric_valid_bias(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$current_metric_valid_bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$current_metric_valid_bias(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample190(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample190())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing current_metric_valid_bias of type double[][] from
	 * the Sandwood model.
	 */
    public final ComputedObjectArray<double[]> current_metric_valid_bias = $current_metric_valid_bias;

    private final ComputedObjectArrayInternal<double[]> $current_metric_var = new ComputedObjectArrayInternal<double[]>(this, "current_metric_var", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$current_metric_var(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$current_metric_var(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$current_metric_var(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample162(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample162())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing current_metric_var of type double[][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[]> current_metric_var = $current_metric_var;

    private final ComputedDoubleArrayInternal $initialStateDistribution = new ComputedDoubleArrayInternal(this, "initialStateDistribution", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$initialStateDistribution(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$initialStateDistribution(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$initialStateDistribution(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample20(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample20())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing initialStateDistribution of type double[] from the
	 * Sandwood model.
	 */
    public final ComputedDoubleArray initialStateDistribution = $initialStateDistribution;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$m(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$m(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample33(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample33())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedObjectArrayInternal<double[][]> $metric_g = new ComputedObjectArrayInternal<double[][]>(this, "metric_g", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() { return state.get$metric_g(); }

        @Override
        protected void setValueInternal(double[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable metric_g because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_g(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/**
	 * Computed variable representing metric_g of type double[][][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[][]> metric_g = $metric_g;

    private final ComputedObjectArrayInternal<boolean[][]> $metric_valid_g = new ComputedObjectArrayInternal<boolean[][]>(this, "metric_valid_g", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 3) {
        @Override
        public boolean[][][] getValue() { return state.get$metric_valid_g(); }

        @Override
        protected void setValueInternal(boolean[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable metric_valid_g because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_valid_g(); }

        @Override
        public boolean[][][][] constructArray(int iterations) {
            return new boolean[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/**
	 * Computed variable representing metric_valid_g of type boolean[][][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<boolean[][]> metric_valid_g = $metric_valid_g;

    private final ComputedObjectArrayInternal<int[]> $st = new ComputedObjectArrayInternal<int[]>(this, "st", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$st(); }

        @Override
        protected void setValueInternal(int[][] value) {
            state.set$st(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$st(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample57(fixed, allocated);
                state.set$fixedFlag$sample76(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample57 = state.get$fixedFlag$sample57();
            boolean fixedFlag$sample76 = state.get$fixedFlag$sample76();
            if(fixedFlag$sample57 && fixedFlag$sample76)
                return Immutability.FIXED;
            else if(fixedFlag$sample57 || fixedFlag$sample76)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $max_metric = new ObservedIntegerInternal(this, "max_metric") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$max_metric();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$max_metric(value, allocated); }
    };

	/** Observed variable representing max_metric of type int from the Sandwood model. */
    public final ObservedInteger max_metric = $max_metric;

    private final ObservedIntegerInternal $noStates = new ObservedIntegerInternal(this, "noStates") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noStates();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noStates(value, allocated); }
    };

	/** Observed variable representing noStates of type int from the Sandwood model. */
    public final ObservedInteger noStates = $noStates;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[][]> $metric_valid = new ObservedObjectArrayInternal<boolean[][]>(this, "metric_valid", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 3) {
        @Override
        public boolean[][][] getValue() {
            synchronized(model) {
                return state.get$metric_valid();
            }
        }

        @Override
        protected void setValueInternal(boolean[][][] value) { state.set$metric_valid(value, allocated); }
    };

	/**
	 * Observed variable representing metric_valid of type boolean[][][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArray<boolean[][]> metric_valid = $metric_valid;

    private final ObservedObjectArrayShapeableInternal<double[][], int[][]> $metric = new ObservedObjectArrayShapeableInternal<double[][], int[][]>(this, "metric", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() {
            synchronized(model) {
                return state.get$metric();
            }
        }

        @Override
        public void setValueInternal(double[][][] value) {
            state.set$metric(value, allocated);
            state.set$length$metric(getDims(value), allocated);
        }

        @Override
        public void setShapeInternal(int[][] shape) {
            state.set$length$metric(shape, allocated);
        }

        @Override
        public int[][] getShape() {
            return state.get$length$metric();
        }
        private final int[][] getDims(double[][][] v2) {
            int[][] s2 = new int[v2.length][];
            for(int i2 = 0; i2 < v2.length; i2++) {
                double[][] v1 = v2[i2];
                int[] s1 = new int[v1.length];
                for(int i1 = 0; i1 < v1.length; i1++) {
                    double[] v0 = v1[i1];
                    s1[i1] = v0.length;
                }
                s2[i2] = s1;
            }
            return s2;
        }
    };

	/**
	 * Observed variable representing metric of type double[][][] from the Sandwood model.
	 */
    public final ObservedObjectArrayShapeable<double[][], int[][]> metric = $metric;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$current_metric_mean, $current_metric_valid_bias, $current_metric_var, $initialStateDistribution, $m, $metric_g, $metric_valid_g, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMMMetrics4() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("current_metric_mean", $current_metric_mean);
        $computedVariables.put("current_metric_valid_bias", $current_metric_valid_bias);
        $computedVariables.put("current_metric_var", $current_metric_var);
        $computedVariables.put("initialStateDistribution", $initialStateDistribution);
        $computedVariables.put("m", $m);
        $computedVariables.put("metric_g", $metric_g);
        $computedVariables.put("metric_valid_g", $metric_valid_g);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("max_metric", $max_metric);
        $modelInputs.put("noStates", $noStates);

        //Observed scalar fields
        $regularObservedValues.put("metric_valid", $metric_valid);

        //Observed array fields
        $shapedObservedValues.put("metric", $metric);

        HMMMetrics4$SingleThreadCPU core = new HMMMetrics4$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param metricShape An integer array describing the shape of variable metric to
	 *                    use in the model when generating results.
	 * @param max_metric The value to set max_metric to.
	 * @param noStates The value to set noStates to.
	 */
    public HMMMetrics4(int[][] metricShape, int max_metric, int noStates) {
        this();
        this.$max_metric.setValue(max_metric);
        this.$noStates.setValue(noStates);
        this.$metric.setShape(metricShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param metric The value to set metric to.
	 * @param metric_valid The value to set metric_valid to
	 * @param max_metric The value to set max_metric to
	 * @param noStates The value to set noStates to
	 */
    public HMMMetrics4(double[][][] metric, boolean[][][] metric_valid, int max_metric, int noStates) {
        this();
        this.metric.setValue(metric);
        this.metric_valid.setValue(metric_valid);
        this.max_metric.setValue(max_metric);
        this.noStates.setValue(noStates);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMMMetrics4$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMMMetrics4$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input metric */
        public final int[][] metricShape;
		/** Field holding the value of model input max_metric */
        public final int max_metric;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param metricShape An integer array describing the shape of variable metric to
		 *                    use in the model when generating results.
		 * @param max_metric The value to set max_metric to.
		 * @param noStates The value to set noStates to.
		 */
        public InferValueInputs(int[][] metricShape, int max_metric, int noStates) {
            this.max_metric = max_metric;
            this.noStates = noStates;
            this.metricShape = metricShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input metric */
        public final double[][][] metric;
		/** Field holding the value of model input metric_valid */
        public final boolean[][][] metric_valid;
		/** Field holding the value of model input max_metric */
        public final int max_metric;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param metric The value to set metric to.
		 * @param metric_valid The value to set metric_valid to.
		 * @param max_metric The value to set max_metric to.
		 * @param noStates The value to set noStates to.
		 */
        public AllInputs(double[][][] metric, boolean[][][] metric_valid, int max_metric, int noStates) {
            this.metric = metric;
            this.metric_valid = metric_valid;
            this.max_metric = max_metric;
            this.noStates = noStates;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/**
		 * Field holding the value of current_metric_mean after a convention execution step.
		 */
        public final double[][] current_metric_mean;
		/**
		 * Field holding the value of current_metric_valid_bias after a convention execution
		 * step.
		 */
        public final double[][] current_metric_valid_bias;
		/**
		 * Field holding the value of current_metric_var after a convention execution step.
		 */
        public final double[][] current_metric_var;
		/**
		 * Field holding the value of initialStateDistribution after a convention execution
		 * step.
		 */
        public final double[] initialStateDistribution;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of metric_g after a convention execution step. */
        public final double[][][] metric_g;
		/** Field holding the value of metric_valid_g after a convention execution step. */
        public final boolean[][][] metric_valid_g;
		/** Field holding the value of st after a convention execution step. */
        public final int[][] st;

        InferredValueOutputs(HMMMetrics4 system$model) {
            this.current_metric_mean = system$model.current_metric_mean.getSamples()[0];
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getSamples()[0];
            this.current_metric_var = system$model.current_metric_var.getSamples()[0];
            this.initialStateDistribution = system$model.initialStateDistribution.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.metric_g = system$model.metric_g.getSamples()[0];
            this.metric_valid_g = system$model.metric_valid_g.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable current_metric_mean */
        public final double current_metric_mean;
		/**
		 * Field holding the log probability of computed variable current_metric_valid_bias
		 */
        public final double current_metric_valid_bias;
		/** Field holding the log probability of computed variable current_metric_var */
        public final double current_metric_var;
		/** Field holding the log probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable metric_g */
        public final double metric_g;
		/** Field holding the log probability of computed variable metric_valid_g */
        public final double metric_valid_g;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMMetrics4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.current_metric_mean = system$model.current_metric_mean.getLogProbability();
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getLogProbability();
            this.current_metric_var = system$model.current_metric_var.getLogProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.metric_g = system$model.metric_g.getLogProbability();
            this.metric_valid_g = system$model.metric_valid_g.getLogProbability();
            this.st = system$model.st.getLogProbability();
        }

		/**
		 * Method to return log probability of the whole model
		 * @return The log probability of the whole model.
		 */
        public double getModelProbability() { return $logModelProbability; }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class Probabilities {
        private final double $modelProbability;
		/** Field holding the probability of computed variable current_metric_mean */
        public final double current_metric_mean;
		/** Field holding the probability of computed variable current_metric_valid_bias */
        public final double current_metric_valid_bias;
		/** Field holding the probability of computed variable current_metric_var */
        public final double current_metric_var;
		/** Field holding the probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable metric_g */
        public final double metric_g;
		/** Field holding the probability of computed variable metric_valid_g */
        public final double metric_valid_g;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMMetrics4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.current_metric_mean = system$model.current_metric_mean.getProbability();
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getProbability();
            this.current_metric_var = system$model.current_metric_var.getProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getProbability();
            this.m = system$model.m.getProbability();
            this.metric_g = system$model.metric_g.getProbability();
            this.metric_valid_g = system$model.metric_valid_g.getProbability();
            this.st = system$model.st.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/**
		 * Field holding the MAP or Sample value of current_metric_mean after an infer model
		 * call.
		 */
        public final double[][][] current_metric_mean;
		/**
		 * Field holding the MAP or Sample value of current_metric_valid_bias after an infer
		 * model call.
		 */
        public final double[][][] current_metric_valid_bias;
		/**
		 * Field holding the MAP or Sample value of current_metric_var after an infer model
		 * call.
		 */
        public final double[][][] current_metric_var;
		/**
		 * Field holding the MAP or Sample value of initialStateDistribution after an infer
		 * model call.
		 */
        public final double[][] initialStateDistribution;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][][] st;

        InferredModelOutputs(HMMMetrics4 system$model) {
            this.current_metric_mean = system$model.getInferredValue(system$model.$current_metric_mean);
            this.current_metric_valid_bias = system$model.getInferredValue(system$model.$current_metric_valid_bias);
            this.current_metric_var = system$model.getInferredValue(system$model.$current_metric_var);
            this.initialStateDistribution = system$model.getInferredValue(system$model.$initialStateDistribution);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setShape(inputs.metricShape);
        execute();
        return new InferredValueOutputs(this);
    }

	/**
	 * Infer the values of the different elements of the model.
	 * @param iterations The number of iterations to perform when inferring the values.
	 * @param inputs An object containing the parameters required to generate the model
	 *               parameters.
	 * @return An object containing the computed values for the model.
	 */
    public InferredModelOutputs inferValues(int iterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferValues(iterations);
        return new InferredModelOutputs(this);
    }

	/**
	 * Generate the probabilities of the different elements of the model.
	 * @param iterations How many iterations should be used to generate these values?
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(int iterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(iterations);
        return new Probabilities(this);
    }

	/**
	 * Calculate the probability of each variable and the overall model. This method will
	 * iterate until the variance of the overall model drops below the value provide for
	 * variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations);
        return new Probabilities(this);
    }

	/**
	 * Calculate the probability of each variable and the overall model. This method will
	 * iterate until the variance of the overall model drops below the value provide for
	 * variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param maxIterations The maximum number of iterations a that can be used to calculate
	 *                      the probabilities. If the model has not converged by this
	 *                      point the calculation will terminate anyway, and the result
	 *                      generated so far will be returned.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new Probabilities(this);
    }

	/**
	 * Generate the log probabilities of the different elements of the model.
	 * @param iterations How many iterations should be used to generate these values?
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(int iterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(iterations);
        return new LogProbabilities(this);
    }

	/**
	 * Calculate the log probability of each variable and the overall model. This method
	 * will iterate until the variance of the overall model drops below the value provide
	 * for variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations);
        return new LogProbabilities(this);
    }

	/**
	 * Calculate the log probability of each variable and the overall model. This method
	 * will iterate until the variance of the overall model drops below the value provide
	 * for variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param maxIterations The maximum number of iterations a that can be used to calculate
	 *                      the probabilities. If the model has not converged by this
	 *                      point the calculation will terminate anyway, and the result
	 *                      generated so far will be returned.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}