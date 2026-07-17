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
 * Class representing the Sandwood model HMMMetrics2 This is the class that all user
 * interactions with the model should occur through.
 */
public final class HMMMetrics2 extends Model<HMMMetrics2.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean[] constrainedFlag$sample104;
		boolean[][] constrainedFlag$sample123;
		boolean constrainedFlag$sample19 = true;
		boolean[] constrainedFlag$sample32;
		boolean[] constrainedFlag$sample52;
		boolean[] constrainedFlag$sample68;
		boolean[] constrainedFlag$sample84;
		double[][] distribution$sample104;
		double[][][] distribution$sample123;
		boolean fixedFlag$sample104 = false;
		boolean fixedFlag$sample123 = false;
		boolean fixedFlag$sample157 = false;
		boolean fixedFlag$sample19 = false;
		boolean fixedFlag$sample32 = false;
		boolean fixedFlag$sample52 = false;
		boolean fixedFlag$sample68 = false;
		boolean fixedFlag$sample84 = false;
		boolean fixedProbFlag$sample104 = false;
		boolean fixedProbFlag$sample123 = false;
		boolean fixedProbFlag$sample145 = false;
		boolean fixedProbFlag$sample157 = false;
		boolean fixedProbFlag$sample19 = false;
		boolean fixedProbFlag$sample32 = false;
		boolean fixedProbFlag$sample52 = false;
		boolean fixedProbFlag$sample68 = false;
		boolean fixedProbFlag$sample84 = false;
		double[] initialStateDistribution;
		int[] length$metric;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$initialStateDistribution;
		double logProbability$m;
		double logProbability$metric_g;
		double logProbability$metric_mean;
		double logProbability$metric_valid_1d;
		double logProbability$metric_valid_bias;
		double logProbability$metric_valid_g;
		double logProbability$metric_var;
		double logProbability$st;
		double logProbability$var102;
		double logProbability$var121;
		double logProbability$var141;
		double logProbability$var151;
		double logProbability$var32;
		double logProbability$var51;
		double logProbability$var67;
		double logProbability$var83;
		double[][] m;
		double[][] metric;
		double[][] metric_g;
		double[] metric_mean;
		boolean[][] metric_valid;
		double[] metric_valid_bias;
		boolean[][] metric_valid_g;
		double[] metric_var;
		int noSamples;
		int noStates;
		int[][] st;
		boolean system$gibbsForward = true;
		double[] v;
		double[][] var151;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			v = new double[noStates];
			
			// If initialStateDistribution has not been set already allocate space.
			if(!fixedFlag$sample19)
				// Constructor for initialStateDistribution
				initialStateDistribution = new double[noStates];
			
			// If m has not been set already allocate space.
			if(!fixedFlag$sample32) {
				// Constructor for m
				m = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					m[var31] = new double[noStates];
			}
			
			// If st has not been set already allocate space.
			if((!fixedFlag$sample104 || !fixedFlag$sample123)) {
				// Constructor for st
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
			
			// Constructor for metric_g
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
			
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
			
			// If metric_mean has not been set already allocate space.
			if(!fixedFlag$sample52)
				// Constructor for metric_mean
				metric_mean = new double[noStates];
			
			// If metric_var has not been set already allocate space.
			if(!fixedFlag$sample68)
				// Constructor for metric_var
				metric_var = new double[noStates];
			
			// If metric_valid_bias has not been set already allocate space.
			if(!fixedFlag$sample84)
				// Constructor for metric_valid_bias
				metric_valid_bias = new double[noStates];
			
			// Constructor for var151
			var151 = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				var151[sample] = new double[length$metric[sample]];
			
			// Constructor for distribution$sample104
			distribution$sample104 = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample104[sample] = new double[noStates];
			
			// Constructor for distribution$sample123
			distribution$sample123 = new double[length$metric.length][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
				distribution$sample123[sample] = subarray$0;
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					subarray$0[(timeStep$var113 - 1)] = new double[noStates];
			}
			
			// Constructor for constrainedFlag$sample32
			constrainedFlag$sample32 = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample123
			constrainedFlag$sample123 = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				constrainedFlag$sample123[sample] = new boolean[(length$metric[sample] - 1)];
			
			// Constructor for constrainedFlag$sample104
			constrainedFlag$sample104 = new boolean[length$metric.length];
			
			// Constructor for constrainedFlag$sample84
			constrainedFlag$sample84 = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample68
			constrainedFlag$sample68 = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample52
			constrainedFlag$sample52 = new boolean[noStates];
		}

		// Getter for distribution$sample104.
		final double[][] get$distribution$sample104() {
			return distribution$sample104;
		}

		// Setter for distribution$sample104.
		final void set$distribution$sample104(double[][] cv$value, boolean allocated$) {
			distribution$sample104 = cv$value;
		}

		// Getter for distribution$sample123.
		final double[][][] get$distribution$sample123() {
			return distribution$sample123;
		}

		// Setter for distribution$sample123.
		final void set$distribution$sample123(double[][][] cv$value, boolean allocated$) {
			distribution$sample123 = cv$value;
		}

		// Getter for fixedFlag$sample104.
		final boolean get$fixedFlag$sample104() {
			return fixedFlag$sample104;
		}

		// Setter for fixedFlag$sample104.
		final void set$fixedFlag$sample104(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample104 including if probabilities
			// need to be updated.
			fixedFlag$sample104 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
					constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
			}
			
			// Should the probability of sample 104 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample104" with its value "cv$value".
			fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
			
			// Should the probability of sample 123 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample104" with its value "cv$value".
			fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
			
			// Should the probability of sample 145 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample104" with its value "cv$value".
			fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
			
			// Should the probability of sample 157 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample104" with its value "cv$value".
			fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		}

		// Getter for fixedFlag$sample123.
		final boolean get$fixedFlag$sample123() {
			return fixedFlag$sample123;
		}

		// Setter for fixedFlag$sample123.
		final void set$fixedFlag$sample123(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample123 including if probabilities
			// need to be updated.
			fixedFlag$sample123 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
					boolean[] cv$constrainedFlag$sample123$1 = constrainedFlag$sample123[index$constrainedFlag$sample123$1];
					for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
						cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
				}
			}
			
			// Should the probability of sample 123 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample123" with its value "cv$value".
			fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
			
			// Should the probability of sample 145 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample123" with its value "cv$value".
			fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
			
			// Should the probability of sample 157 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample123" with its value "cv$value".
			fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		}

		// Getter for fixedFlag$sample157.
		final boolean get$fixedFlag$sample157() {
			return fixedFlag$sample157;
		}

		// Setter for fixedFlag$sample157.
		final void set$fixedFlag$sample157(boolean cv$value, boolean allocated$) {
			fixedFlag$sample157 = cv$value;
		}

		// Getter for fixedFlag$sample19.
		final boolean get$fixedFlag$sample19() {
			return fixedFlag$sample19;
		}

		// Setter for fixedFlag$sample19.
		final void set$fixedFlag$sample19(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample19 including if probabilities
			// need to be updated.
			fixedFlag$sample19 = cv$value;
			
			// Substituted "fixedFlag$sample19" with its value "cv$value".
			constrainedFlag$sample19 = (cv$value || constrainedFlag$sample19);
			
			// Should the probability of sample 19 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample19" with its value "cv$value".
			fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
			
			// Should the probability of sample 104 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample19" with its value "cv$value".
			fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		}

		// Getter for fixedFlag$sample32.
		final boolean get$fixedFlag$sample32() {
			return fixedFlag$sample32;
		}

		// Setter for fixedFlag$sample32.
		final void set$fixedFlag$sample32(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
			// need to be updated.
			fixedFlag$sample32 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
					constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
			}
			
			// Should the probability of sample 32 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample32" with its value "cv$value".
			fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
			
			// Should the probability of sample 123 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample32" with its value "cv$value".
			fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		}

		// Getter for fixedFlag$sample52.
		final boolean get$fixedFlag$sample52() {
			return fixedFlag$sample52;
		}

		// Setter for fixedFlag$sample52.
		final void set$fixedFlag$sample52(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
			// need to be updated.
			fixedFlag$sample52 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
					constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
			}
			
			// Should the probability of sample 52 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample52" with its value "cv$value".
			fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
			
			// Should the probability of sample 157 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample52" with its value "cv$value".
			fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		}

		// Getter for fixedFlag$sample68.
		final boolean get$fixedFlag$sample68() {
			return fixedFlag$sample68;
		}

		// Setter for fixedFlag$sample68.
		final void set$fixedFlag$sample68(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample68 including if probabilities
			// need to be updated.
			fixedFlag$sample68 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
					constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
			}
			
			// Should the probability of sample 68 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample68" with its value "cv$value".
			fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
			
			// Should the probability of sample 157 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample68" with its value "cv$value".
			fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		}

		// Getter for fixedFlag$sample84.
		final boolean get$fixedFlag$sample84() {
			return fixedFlag$sample84;
		}

		// Setter for fixedFlag$sample84.
		final void set$fixedFlag$sample84(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
			// need to be updated.
			fixedFlag$sample84 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
					constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
			}
			
			// Should the probability of sample 84 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample84" with its value "cv$value".
			fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
			
			// Should the probability of sample 145 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample84" with its value "cv$value".
			fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
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
			
			// Unset the fixed probability flag for sample 19 as it depends on initialStateDistribution.
			fixedProbFlag$sample19 = false;
			
			// Unset the fixed probability flag for sample 104 as it depends on initialStateDistribution.
			fixedProbFlag$sample104 = false;
		}

		// Getter for length$metric.
		final int[] get$length$metric() {
			return length$metric;
		}

		// Setter for length$metric.
		final void set$length$metric(int[] cv$value, boolean allocated$) {
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

		// Getter for logProbability$metric_mean.
		final double get$logProbability$metric_mean() {
			return logProbability$metric_mean;
		}

		// Getter for logProbability$metric_valid_bias.
		final double get$logProbability$metric_valid_bias() {
			return logProbability$metric_valid_bias;
		}

		// Getter for logProbability$metric_valid_g.
		final double get$logProbability$metric_valid_g() {
			return logProbability$metric_valid_g;
		}

		// Getter for logProbability$metric_var.
		final double get$logProbability$metric_var() {
			return logProbability$metric_var;
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
			
			// Unset the fixed probability flag for sample 32 as it depends on m.
			fixedProbFlag$sample32 = false;
			
			// Unset the fixed probability flag for sample 123 as it depends on m.
			fixedProbFlag$sample123 = false;
		}

		// Getter for metric.
		final double[][] get$metric() {
			return metric;
		}

		// Setter for metric.
		final void set$metric(double[][] cv$value, boolean allocated$) {
			metric = cv$value;
		}

		// Getter for metric_g.
		final double[][] get$metric_g() {
			return metric_g;
		}

		// Getter for metric_mean.
		final double[] get$metric_mean() {
			return metric_mean;
		}

		// Setter for metric_mean.
		final void set$metric_mean(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of metric_mean including if probabilities need
			// to be updated.
			metric_mean = cv$value;
			
			// Unset the fixed probability flag for sample 52 as it depends on metric_mean.
			fixedProbFlag$sample52 = false;
			
			// Unset the fixed probability flag for sample 157 as it depends on metric_mean.
			fixedProbFlag$sample157 = false;
		}

		// Getter for metric_valid.
		final boolean[][] get$metric_valid() {
			return metric_valid;
		}

		// Setter for metric_valid.
		final void set$metric_valid(boolean[][] cv$value, boolean allocated$) {
			metric_valid = cv$value;
		}

		// Getter for metric_valid_bias.
		final double[] get$metric_valid_bias() {
			return metric_valid_bias;
		}

		// Setter for metric_valid_bias.
		final void set$metric_valid_bias(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of metric_valid_bias including if probabilities
			// need to be updated.
			metric_valid_bias = cv$value;
			
			// Unset the fixed probability flag for sample 84 as it depends on metric_valid_bias.
			fixedProbFlag$sample84 = false;
			
			// Unset the fixed probability flag for sample 145 as it depends on metric_valid_bias.
			fixedProbFlag$sample145 = false;
		}

		// Getter for metric_valid_g.
		final boolean[][] get$metric_valid_g() {
			return metric_valid_g;
		}

		// Getter for metric_var.
		final double[] get$metric_var() {
			return metric_var;
		}

		// Setter for metric_var.
		final void set$metric_var(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of metric_var including if probabilities need
			// to be updated.
			metric_var = cv$value;
			
			// Unset the fixed probability flag for sample 68 as it depends on metric_var.
			fixedProbFlag$sample68 = false;
			
			// Unset the fixed probability flag for sample 157 as it depends on metric_var.
			fixedProbFlag$sample157 = false;
		}

		// Getter for noSamples.
		final int get$noSamples() {
			return noSamples;
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
			
			// Unset the fixed probability flag for sample 104 as it depends on st.
			fixedProbFlag$sample104 = false;
			
			// Unset the fixed probability flag for sample 123 as it depends on st.
			fixedProbFlag$sample123 = false;
			
			// Unset the fixed probability flag for sample 145 as it depends on st.
			fixedProbFlag$sample145 = false;
			
			// Unset the fixed probability flag for sample 157 as it depends on st.
			fixedProbFlag$sample157 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}
	}

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
                state.set$fixedFlag$sample19(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample19())
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
                state.set$fixedFlag$sample32(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample32())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedObjectArrayInternal<double[]> $metric_g = new ComputedObjectArrayInternal<double[]>(this, "metric_g", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$metric_g(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable metric_g because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_g(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
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
	 * Computed variable representing metric_g of type double[][] from the Sandwood model.
	 */
    public final ComputedObjectArray<double[]> metric_g = $metric_g;

    private final ComputedDoubleArrayInternal $metric_mean = new ComputedDoubleArrayInternal(this, "metric_mean", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$metric_mean(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$metric_mean(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_mean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample52(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample52())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing metric_mean of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray metric_mean = $metric_mean;

    private final ComputedDoubleArrayInternal $metric_valid_bias = new ComputedDoubleArrayInternal(this, "metric_valid_bias", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$metric_valid_bias(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$metric_valid_bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_valid_bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample84(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample84())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing metric_valid_bias of type double[] from the Sandwood
	 * model.
	 */
    public final ComputedDoubleArray metric_valid_bias = $metric_valid_bias;

    private final ComputedObjectArrayInternal<boolean[]> $metric_valid_g = new ComputedObjectArrayInternal<boolean[]>(this, "metric_valid_g", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return state.get$metric_valid_g(); }

        @Override
        protected void setValueInternal(boolean[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable metric_valid_g because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_valid_g(); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
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
	 * Computed variable representing metric_valid_g of type boolean[][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<boolean[]> metric_valid_g = $metric_valid_g;

    private final ComputedDoubleArrayInternal $metric_var = new ComputedDoubleArrayInternal(this, "metric_var", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$metric_var(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$metric_var(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$metric_var(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample68(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample68())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing metric_var of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray metric_var = $metric_var;

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
                state.set$fixedFlag$sample104(fixed, allocated);
                state.set$fixedFlag$sample123(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample104 = state.get$fixedFlag$sample104();
            boolean fixedFlag$sample123 = state.get$fixedFlag$sample123();
            if(fixedFlag$sample104 && fixedFlag$sample123)
                return Immutability.FIXED;
            else if(fixedFlag$sample104 || fixedFlag$sample123)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

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

    private final ObservedObjectArrayInternal<boolean[]> $metric_valid = new ObservedObjectArrayInternal<boolean[]>(this, "metric_valid", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return state.get$metric_valid();
            }
        }

        @Override
        protected void setValueInternal(boolean[][] value) { state.set$metric_valid(value, allocated); }
    };

	/**
	 * Observed variable representing metric_valid of type boolean[][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArray<boolean[]> metric_valid = $metric_valid;

    private final ObservedObjectArrayShapeableInternal<double[], int[]> $metric = new ObservedObjectArrayShapeableInternal<double[], int[]>(this, "metric", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() {
            synchronized(model) {
                return state.get$metric();
            }
        }

        @Override
        public void setValueInternal(double[][] value) {
            state.set$metric(value, allocated);
            state.set$length$metric(getDims(value), allocated);
        }

        @Override
        public void setShapeInternal(int[] shape) {
            state.set$length$metric(shape, allocated);
        }

        @Override
        public int[] getShape() {
            return state.get$length$metric();
        }
        private final int[] getDims(double[][] v1) {
            int[] s1 = new int[v1.length];
            for(int i1 = 0; i1 < v1.length; i1++) {
                double[] v0 = v1[i1];
                s1[i1] = v0.length;
            }
            return s1;
        }
    };

	/**
	 * Observed variable representing metric of type double[][] from the Sandwood model.
	 */
    public final ObservedObjectArrayShapeable<double[], int[]> metric = $metric;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$initialStateDistribution, $m, $metric_g, $metric_mean, $metric_valid_bias, $metric_valid_g, $metric_var, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMMMetrics2() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("initialStateDistribution", $initialStateDistribution);
        $computedVariables.put("m", $m);
        $computedVariables.put("metric_g", $metric_g);
        $computedVariables.put("metric_mean", $metric_mean);
        $computedVariables.put("metric_valid_bias", $metric_valid_bias);
        $computedVariables.put("metric_valid_g", $metric_valid_g);
        $computedVariables.put("metric_var", $metric_var);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("noStates", $noStates);

        //Observed scalar fields
        $regularObservedValues.put("metric_valid", $metric_valid);

        //Observed array fields
        $shapedObservedValues.put("metric", $metric);

        HMMMetrics2$SingleThreadCPU core = new HMMMetrics2$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param metricShape An integer array describing the shape of variable metric to
	 *                    use in the model when generating results.
	 * @param noStates The value to set noStates to.
	 */
    public HMMMetrics2(int[] metricShape, int noStates) {
        this();
        this.$noStates.setValue(noStates);
        this.$metric.setShape(metricShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param metric The value to set metric to.
	 * @param metric_valid The value to set metric_valid to
	 * @param noStates The value to set noStates to
	 */
    public HMMMetrics2(double[][] metric, boolean[][] metric_valid, int noStates) {
        this();
        this.metric.setValue(metric);
        this.metric_valid.setValue(metric_valid);
        this.noStates.setValue(noStates);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMMMetrics2$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMMMetrics2$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input metric */
        public final int[] metricShape;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param metricShape An integer array describing the shape of variable metric to
		 *                    use in the model when generating results.
		 * @param noStates The value to set noStates to.
		 */
        public InferValueInputs(int[] metricShape, int noStates) {
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
        public final double[][] metric;
		/** Field holding the value of model input metric_valid */
        public final boolean[][] metric_valid;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param metric The value to set metric to.
		 * @param metric_valid The value to set metric_valid to.
		 * @param noStates The value to set noStates to.
		 */
        public AllInputs(double[][] metric, boolean[][] metric_valid, int noStates) {
            this.metric = metric;
            this.metric_valid = metric_valid;
            this.noStates = noStates;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/**
		 * Field holding the value of initialStateDistribution after a convention execution
		 * step.
		 */
        public final double[] initialStateDistribution;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of metric_g after a convention execution step. */
        public final double[][] metric_g;
		/** Field holding the value of metric_mean after a convention execution step. */
        public final double[] metric_mean;
		/** Field holding the value of metric_valid_bias after a convention execution step. */
        public final double[] metric_valid_bias;
		/** Field holding the value of metric_valid_g after a convention execution step. */
        public final boolean[][] metric_valid_g;
		/** Field holding the value of metric_var after a convention execution step. */
        public final double[] metric_var;
		/** Field holding the value of st after a convention execution step. */
        public final int[][] st;

        InferredValueOutputs(HMMMetrics2 system$model) {
            this.initialStateDistribution = system$model.initialStateDistribution.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.metric_g = system$model.metric_g.getSamples()[0];
            this.metric_mean = system$model.metric_mean.getSamples()[0];
            this.metric_valid_bias = system$model.metric_valid_bias.getSamples()[0];
            this.metric_valid_g = system$model.metric_valid_g.getSamples()[0];
            this.metric_var = system$model.metric_var.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable metric_g */
        public final double metric_g;
		/** Field holding the log probability of computed variable metric_mean */
        public final double metric_mean;
		/** Field holding the log probability of computed variable metric_valid_bias */
        public final double metric_valid_bias;
		/** Field holding the log probability of computed variable metric_valid_g */
        public final double metric_valid_g;
		/** Field holding the log probability of computed variable metric_var */
        public final double metric_var;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMMetrics2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.metric_g = system$model.metric_g.getLogProbability();
            this.metric_mean = system$model.metric_mean.getLogProbability();
            this.metric_valid_bias = system$model.metric_valid_bias.getLogProbability();
            this.metric_valid_g = system$model.metric_valid_g.getLogProbability();
            this.metric_var = system$model.metric_var.getLogProbability();
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
		/** Field holding the probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable metric_g */
        public final double metric_g;
		/** Field holding the probability of computed variable metric_mean */
        public final double metric_mean;
		/** Field holding the probability of computed variable metric_valid_bias */
        public final double metric_valid_bias;
		/** Field holding the probability of computed variable metric_valid_g */
        public final double metric_valid_g;
		/** Field holding the probability of computed variable metric_var */
        public final double metric_var;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMMetrics2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getProbability();
            this.m = system$model.m.getProbability();
            this.metric_g = system$model.metric_g.getProbability();
            this.metric_mean = system$model.metric_mean.getProbability();
            this.metric_valid_bias = system$model.metric_valid_bias.getProbability();
            this.metric_valid_g = system$model.metric_valid_g.getProbability();
            this.metric_var = system$model.metric_var.getProbability();
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
		 * Field holding the MAP or Sample value of initialStateDistribution after an infer
		 * model call.
		 */
        public final double[][] initialStateDistribution;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of metric_mean after an infer model call. */
        public final double[][] metric_mean;
		/**
		 * Field holding the MAP or Sample value of metric_valid_bias after an infer model
		 * call.
		 */
        public final double[][] metric_valid_bias;
		/** Field holding the MAP or Sample value of metric_var after an infer model call. */
        public final double[][] metric_var;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][][] st;

        InferredModelOutputs(HMMMetrics2 system$model) {
            this.initialStateDistribution = system$model.getInferredValue(system$model.$initialStateDistribution);
            this.m = system$model.getInferredValue(system$model.$m);
            this.metric_mean = system$model.getInferredValue(system$model.$metric_mean);
            this.metric_valid_bias = system$model.getInferredValue(system$model.$metric_valid_bias);
            this.metric_var = system$model.getInferredValue(system$model.$metric_var);
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
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}