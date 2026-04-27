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
 * Class representing the Sandwood model HMM_Mk2 This is the class that all user interactions
 * with the model should occur through.
 */
public final class HMM_Mk2 extends Model<HMM_Mk2.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[][] bias;
		boolean[][] constrainedFlag$sample126;
		boolean[] constrainedFlag$sample42;
		boolean[] constrainedFlag$sample57;
		boolean constrainedFlag$sample78 = true;
		boolean constrainedFlag$sample80 = true;
		boolean[] constrainedFlag$sample95;
		int[][] events;
		int[][] eventsMeasured;
		boolean fixedFlag$sample126 = false;
		boolean fixedFlag$sample42 = false;
		boolean fixedFlag$sample57 = false;
		boolean fixedFlag$sample78 = false;
		boolean fixedFlag$sample80 = false;
		boolean fixedFlag$sample95 = false;
		boolean fixedProbFlag$sample126 = false;
		boolean fixedProbFlag$sample159 = false;
		boolean fixedProbFlag$sample42 = false;
		boolean fixedProbFlag$sample57 = false;
		boolean fixedProbFlag$sample78 = false;
		boolean fixedProbFlag$sample80 = false;
		boolean fixedProbFlag$sample95 = false;
		int initialState;
		int[] length$eventsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$events;
		double logProbability$initialState;
		double logProbability$m;
		double logProbability$st;
		double logProbability$var123;
		double logProbability$var155;
		double logProbability$var42;
		double logProbability$var56;
		double logProbability$var92;
		double logProbability$weights;
		double[][] m;
		int noEvents;
		int noStates;
		int samples;
		int[][] st;
		boolean system$gibbsForward = true;
		double[] v;
		double[] v2;
		double[] weights;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			v = new double[noStates];
			
			// Constructor for v2
			v2 = new double[noEvents];
			
			// If m has not been set already allocate space.
			if(!fixedFlag$sample42) {
				// Constructor for m
				m = new double[noStates][];
				for(int var41 = 0; var41 < noStates; var41 += 1)
					m[var41] = new double[noStates];
			}
			
			// If bias has not been set already allocate space.
			if(!fixedFlag$sample57) {
				// Constructor for bias
				bias = new double[noStates][];
				for(int var55 = 0; var55 < noStates; var55 += 1)
					bias[var55] = new double[noEvents];
			}
			
			// If st has not been set already allocate space.
			if((!fixedFlag$sample95 || !fixedFlag$sample126)) {
				// Constructor for st
				st = new int[length$eventsMeasured.length][];
				for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
					st[i$var69] = new int[length$eventsMeasured[i$var69]];
			}
			
			// If weights has not been set already allocate space.
			if(!fixedFlag$sample78)
				// Constructor for weights
				weights = new double[noStates];
			
			// Constructor for events
			events = new int[length$eventsMeasured.length][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				events[i$var136] = new int[length$eventsMeasured[i$var136]];
			
			// Constructor for constrainedFlag$sample95
			constrainedFlag$sample95 = new boolean[length$eventsMeasured.length];
			
			// Constructor for constrainedFlag$sample126
			constrainedFlag$sample126 = new boolean[length$eventsMeasured.length][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				constrainedFlag$sample126[i$var104] = new boolean[(length$eventsMeasured[i$var104] - 1)];
			
			// Constructor for constrainedFlag$sample42
			constrainedFlag$sample42 = new boolean[noStates];
			
			// Constructor for constrainedFlag$sample57
			constrainedFlag$sample57 = new boolean[noStates];
		}

		// Getter for bias.
		final double[][] get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of bias including if probabilities need to be
			// updated.
			bias = cv$value;
			
			// Unset the fixed probability flag for sample 57 as it depends on bias.
			fixedProbFlag$sample57 = false;
			
			// Unset the fixed probability flag for sample 159 as it depends on bias.
			fixedProbFlag$sample159 = false;
		}

		// Getter for events.
		final int[][] get$events() {
			return events;
		}

		// Getter for eventsMeasured.
		final int[][] get$eventsMeasured() {
			return eventsMeasured;
		}

		// Setter for eventsMeasured.
		final void set$eventsMeasured(int[][] cv$value, boolean allocated$) {
			eventsMeasured = cv$value;
		}

		// Getter for fixedFlag$sample126.
		final boolean get$fixedFlag$sample126() {
			return fixedFlag$sample126;
		}

		// Setter for fixedFlag$sample126.
		final void set$fixedFlag$sample126(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample126 including if probabilities
			// need to be updated.
			fixedFlag$sample126 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
					boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
					for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
						cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
				}
			}
			
			// Should the probability of sample 126 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample126" with its value "cv$value".
			fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
			
			// Should the probability of sample 159 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample126" with its value "cv$value".
			fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
		}

		// Getter for fixedFlag$sample42.
		final boolean get$fixedFlag$sample42() {
			return fixedFlag$sample42;
		}

		// Setter for fixedFlag$sample42.
		final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
			// need to be updated.
			fixedFlag$sample42 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
					constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
			}
			
			// Should the probability of sample 42 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample42" with its value "cv$value".
			fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
			
			// Should the probability of sample 95 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample42" with its value "cv$value".
			fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
			
			// Should the probability of sample 126 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample42" with its value "cv$value".
			fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
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
			
			// Should the probability of sample 159 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
		}

		// Getter for fixedFlag$sample78.
		final boolean get$fixedFlag$sample78() {
			return fixedFlag$sample78;
		}

		// Setter for fixedFlag$sample78.
		final void set$fixedFlag$sample78(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
			// need to be updated.
			fixedFlag$sample78 = cv$value;
			
			// Substituted "fixedFlag$sample78" with its value "cv$value".
			constrainedFlag$sample78 = (cv$value || constrainedFlag$sample78);
			
			// Should the probability of sample 78 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample78" with its value "cv$value".
			fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
			
			// Should the probability of sample 80 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample78" with its value "cv$value".
			fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		}

		// Getter for fixedFlag$sample80.
		final boolean get$fixedFlag$sample80() {
			return fixedFlag$sample80;
		}

		// Setter for fixedFlag$sample80.
		final void set$fixedFlag$sample80(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample80 including if probabilities
			// need to be updated.
			fixedFlag$sample80 = cv$value;
			
			// Substituted "fixedFlag$sample80" with its value "cv$value".
			constrainedFlag$sample80 = (cv$value || constrainedFlag$sample80);
			
			// Should the probability of sample 80 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample80" with its value "cv$value".
			fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
			
			// Should the probability of sample 95 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample80" with its value "cv$value".
			fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		}

		// Getter for fixedFlag$sample95.
		final boolean get$fixedFlag$sample95() {
			return fixedFlag$sample95;
		}

		// Setter for fixedFlag$sample95.
		final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample95 including if probabilities
			// need to be updated.
			fixedFlag$sample95 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
					constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
			}
			
			// Should the probability of sample 95 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample95" with its value "cv$value".
			fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
			
			// Should the probability of sample 126 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample95" with its value "cv$value".
			fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
			
			// Should the probability of sample 159 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample95" with its value "cv$value".
			fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
		}

		// Getter for initialState.
		final int get$initialState() {
			return initialState;
		}

		// Setter for initialState.
		final void set$initialState(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of initialState including if probabilities need
			// to be updated.
			initialState = cv$value;
			
			// Unset the fixed probability flag for sample 80 as it depends on initialState.
			fixedProbFlag$sample80 = false;
			
			// Unset the fixed probability flag for sample 95 as it depends on initialState.
			fixedProbFlag$sample95 = false;
		}

		// Getter for length$eventsMeasured.
		final int[] get$length$eventsMeasured() {
			return length$eventsMeasured;
		}

		// Setter for length$eventsMeasured.
		final void set$length$eventsMeasured(int[] cv$value, boolean allocated$) {
			length$eventsMeasured = cv$value;
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

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$events.
		final double get$logProbability$events() {
			return logProbability$events;
		}

		// Getter for logProbability$initialState.
		final double get$logProbability$initialState() {
			return logProbability$initialState;
		}

		// Getter for logProbability$m.
		final double get$logProbability$m() {
			return logProbability$m;
		}

		// Getter for logProbability$st.
		final double get$logProbability$st() {
			return logProbability$st;
		}

		// Getter for logProbability$weights.
		final double get$logProbability$weights() {
			return logProbability$weights;
		}

		// Getter for m.
		final double[][] get$m() {
			return m;
		}

		// Setter for m.
		final void set$m(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of m including if probabilities need to be updated.
			m = cv$value;
			
			// Unset the fixed probability flag for sample 42 as it depends on m.
			fixedProbFlag$sample42 = false;
			
			// Unset the fixed probability flag for sample 95 as it depends on m.
			fixedProbFlag$sample95 = false;
			
			// Unset the fixed probability flag for sample 126 as it depends on m.
			fixedProbFlag$sample126 = false;
		}

		// Getter for noEvents.
		final int get$noEvents() {
			return noEvents;
		}

		// Setter for noEvents.
		final void set$noEvents(int cv$value, boolean allocated$) {
			noEvents = cv$value;
		}

		// Getter for noStates.
		final int get$noStates() {
			return noStates;
		}

		// Setter for noStates.
		final void set$noStates(int cv$value, boolean allocated$) {
			noStates = cv$value;
		}

		// Getter for samples.
		final int get$samples() {
			return samples;
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
			
			// Unset the fixed probability flag for sample 95 as it depends on st.
			fixedProbFlag$sample95 = false;
			
			// Unset the fixed probability flag for sample 126 as it depends on st.
			fixedProbFlag$sample126 = false;
			
			// Unset the fixed probability flag for sample 159 as it depends on st.
			fixedProbFlag$sample159 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}

		// Getter for v2.
		final double[] get$v2() {
			return v2;
		}

		// Getter for weights.
		final double[] get$weights() {
			return weights;
		}

		// Setter for weights.
		final void set$weights(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of weights including if probabilities need to
			// be updated.
			weights = cv$value;
			
			// Unset the fixed probability flag for sample 78 as it depends on weights.
			fixedProbFlag$sample78 = false;
			
			// Unset the fixed probability flag for sample 80 as it depends on weights.
			fixedProbFlag$sample80 = false;
		}
	}

    private final ComputedObjectArrayInternal<double[]> $bias = new ComputedObjectArrayInternal<double[]>(this, "bias", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample57(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample57())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> bias = $bias;

    private final ComputedObjectArrayInternal<int[]> $events = new ComputedObjectArrayInternal<int[]>(this, "events", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$events(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable events because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$events(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
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

	/** Computed variable representing events of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> events = $events;

    private final ComputedIntegerInternal $initialState = new ComputedIntegerInternal(this, "initialState", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$initialState(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$initialState(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$initialState(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample80(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample80())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing initialState of type int from the Sandwood model.
	 */
    public final ComputedInteger initialState = $initialState;

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
                state.set$fixedFlag$sample42(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample42())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

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
                state.set$fixedFlag$sample126(fixed, allocated);
                state.set$fixedFlag$sample95(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample126 = state.get$fixedFlag$sample126();
            boolean fixedFlag$sample95 = state.get$fixedFlag$sample95();
            if(fixedFlag$sample126 && fixedFlag$sample95)
                return Immutability.FIXED;
            else if(fixedFlag$sample126 || fixedFlag$sample95)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> st = $st;

    private final ComputedDoubleArrayInternal $weights = new ComputedDoubleArrayInternal(this, "weights", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$weights(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$weights(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$weights(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample78(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample78())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing weights of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray weights = $weights;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $noEvents = new ObservedIntegerInternal(this, "noEvents") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noEvents();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noEvents(value, allocated); }
    };

	/** Observed variable representing noEvents of type int from the Sandwood model. */
    public final ObservedInteger noEvents = $noEvents;

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

    private final ObservedObjectArrayShapeableInternal<int[], int[]> $eventsMeasured = new ObservedObjectArrayShapeableInternal<int[], int[]>(this, "eventsMeasured", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$eventsMeasured();
            }
        }

        @Override
        public void setValueInternal(int[][] value) {
            state.set$eventsMeasured(value, allocated);
            state.set$length$eventsMeasured(getDims(value), allocated);
        }

        @Override
        public void setShapeInternal(int[] shape) {
            state.set$length$eventsMeasured(shape, allocated);
        }

        @Override
        public int[] getShape() {
            return state.get$length$eventsMeasured();
        }
        private final int[] getDims(int[][] v1) {
            int[] s1 = new int[v1.length];
            for(int i1 = 0; i1 < v1.length; i1++) {
                int[] v0 = v1[i1];
                s1[i1] = v0.length;
            }
            return s1;
        }
    };

	/**
	 * Observed variable representing eventsMeasured of type int[][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArrayShapeable<int[], int[]> eventsMeasured = $eventsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $events, $initialState, $m, $st, $weights};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMM_Mk2() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("events", $events);
        $computedVariables.put("initialState", $initialState);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);
        $computedVariables.put("weights", $weights);

        //ModelInputs
        $modelInputs.put("noEvents", $noEvents);
        $modelInputs.put("noStates", $noStates);

        //Observed array fields
        $shapedObservedValues.put("eventsMeasured", $eventsMeasured);

        HMM_Mk2$SingleThreadCPU core = new HMM_Mk2$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param eventsMeasuredShape An integer array describing the shape of variable eventsMeasured
	 *                            to use in the model when generating results.
	 * @param noStates The value to set noStates to.
	 * @param noEvents The value to set noEvents to.
	 */
    public HMM_Mk2(int[] eventsMeasuredShape, int noStates, int noEvents) {
        this();
        this.$noEvents.setValue(noEvents);
        this.$noStates.setValue(noStates);
        this.$eventsMeasured.setShape(eventsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param eventsMeasured The value to set eventsMeasured to.
	 * @param noStates The value to set noStates to
	 * @param noEvents The value to set noEvents to
	 */
    public HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {
        this();
        this.eventsMeasured.setValue(eventsMeasured);
        this.noStates.setValue(noStates);
        this.noEvents.setValue(noEvents);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMM_Mk2$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMM_Mk2$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input eventsMeasured */
        public final int[] eventsMeasuredShape;
		/** Field holding the value of model input noStates */
        public final int noStates;
		/** Field holding the value of model input noEvents */
        public final int noEvents;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param eventsMeasuredShape An integer array describing the shape of variable eventsMeasured
		 *                            to use in the model when generating results.
		 * @param noStates The value to set noStates to.
		 * @param noEvents The value to set noEvents to.
		 */
        public InferValueInputs(int[] eventsMeasuredShape, int noStates, int noEvents) {
            this.noEvents = noEvents;
            this.noStates = noStates;
            this.eventsMeasuredShape = eventsMeasuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input eventsMeasured */
        public final int[][] eventsMeasured;
		/** Field holding the value of model input noStates */
        public final int noStates;
		/** Field holding the value of model input noEvents */
        public final int noEvents;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param eventsMeasured The value to set eventsMeasured to.
		 * @param noStates The value to set noStates to.
		 * @param noEvents The value to set noEvents to.
		 */
        public AllInputs(int[][] eventsMeasured, int noStates, int noEvents) {
            this.eventsMeasured = eventsMeasured;
            this.noStates = noStates;
            this.noEvents = noEvents;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[][] bias;
		/** Field holding the value of events after a convention execution step. */
        public final int[][] events;
		/** Field holding the value of initialState after a convention execution step. */
        public final int initialState;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of st after a convention execution step. */
        public final int[][] st;
		/** Field holding the value of weights after a convention execution step. */
        public final double[] weights;

        InferredValueOutputs(HMM_Mk2 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.events = system$model.events.getSamples()[0];
            this.initialState = system$model.initialState.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
            this.weights = system$model.weights.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable events */
        public final double events;
		/** Field holding the log probability of computed variable initialState */
        public final double initialState;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable st */
        public final double st;
		/** Field holding the log probability of computed variable weights */
        public final double weights;

        LogProbabilities(HMM_Mk2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.events = system$model.events.getLogProbability();
            this.initialState = system$model.initialState.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
            this.weights = system$model.weights.getLogProbability();
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
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable events */
        public final double events;
		/** Field holding the probability of computed variable initialState */
        public final double initialState;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable st */
        public final double st;
		/** Field holding the probability of computed variable weights */
        public final double weights;

        Probabilities(HMM_Mk2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.events = system$model.events.getProbability();
            this.initialState = system$model.initialState.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
            this.weights = system$model.weights.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[][][] bias;
		/**
		 * Field holding the MAP or Sample value of initialState after an infer model call.
		 */
        public final int[] initialState;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][][] st;
		/** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(HMM_Mk2 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.initialState = system$model.getInferredValue(system$model.$initialState);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
            this.weights = system$model.getInferredValue(system$model.$weights);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setShape(inputs.eventsMeasuredShape);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
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
        this.noEvents.setValue(inputs.noEvents);
        this.noStates.setValue(inputs.noStates);
        this.$eventsMeasured.setValue(inputs.eventsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}