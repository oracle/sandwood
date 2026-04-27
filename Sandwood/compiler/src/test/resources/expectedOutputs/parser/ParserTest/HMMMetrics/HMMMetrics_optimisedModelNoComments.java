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
 * Class representing the Sandwood model HMMMetrics This is the class that all user
 * interactions with the model should occur through.
 */
public final class HMMMetrics extends Model<HMMMetrics.State> {
	final class State extends CoreModelState {
boolean[] constrainedFlag$sample113;
		boolean[] constrainedFlag$sample130;
		boolean[] constrainedFlag$sample147;
		boolean[] constrainedFlag$sample164;
		boolean[] constrainedFlag$sample30;
		boolean constrainedFlag$sample36 = true;
		boolean constrainedFlag$sample39 = true;
		boolean[] constrainedFlag$sample57;
		boolean[] constrainedFlag$sample77;
		boolean[] constrainedFlag$sample95;
		double[] cpu;
		double[] cpuMean;
		double[] cpuVar;
		double[] cpu_measured;
		double[] distribution$sample39;
		double[][] distribution$sample57;
		boolean fixedFlag$sample113 = false;
		boolean fixedFlag$sample130 = false;
		boolean fixedFlag$sample147 = false;
		boolean fixedFlag$sample164 = false;
		boolean fixedFlag$sample30 = false;
		boolean fixedFlag$sample36 = false;
		boolean fixedFlag$sample39 = false;
		boolean fixedFlag$sample57 = false;
		boolean fixedFlag$sample77 = false;
		boolean fixedFlag$sample95 = false;
		boolean fixedProbFlag$sample113 = false;
		boolean fixedProbFlag$sample130 = false;
		boolean fixedProbFlag$sample147 = false;
		boolean fixedProbFlag$sample164 = false;
		boolean fixedProbFlag$sample180 = false;
		boolean fixedProbFlag$sample185 = false;
		boolean fixedProbFlag$sample190 = false;
		boolean fixedProbFlag$sample30 = false;
		boolean fixedProbFlag$sample36 = false;
		boolean fixedProbFlag$sample39 = false;
		boolean fixedProbFlag$sample57 = false;
		boolean fixedProbFlag$sample77 = false;
		boolean fixedProbFlag$sample95 = false;
		double[] initialStateDistribution;
		int length$cpu_measured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$cpu;
		double logProbability$cpuMean;
		double logProbability$cpuVar;
		double logProbability$initialStateDistribution;
		double logProbability$m;
		double logProbability$mem;
		double logProbability$memMean;
		double logProbability$memVar;
		double logProbability$pageFaults;
		double logProbability$pageFaultsMean;
		double logProbability$pageFaultsVar;
		double[] logProbability$sample180;
		double[] logProbability$sample185;
		double[] logProbability$sample190;
		double[] logProbability$sample57;
		double logProbability$st;
		double logProbability$var112;
		double logProbability$var129;
		double logProbability$var146;
		double logProbability$var163;
		double logProbability$var30;
		double logProbability$var38;
		double logProbability$var76;
		double logProbability$var94;
		double[][] m;
		double[] mem;
		double[] memMean;
		double[] memVar;
		double[] mem_measured;
		int noStates;
		double[] pageFaults;
		double[] pageFaultsMean;
		double[] pageFaultsVar;
		double[] pageFaults_measured;
		int samples;
		int[] st;
		boolean system$gibbsForward = true;
		double[] v;

		@Override
		public final void allocate() {
			v = new double[noStates];
			if(!fixedFlag$sample30) {
				m = new double[noStates][];
				for(int var29 = 0; var29 < noStates; var29 += 1)
					m[var29] = new double[noStates];
			}
			if((!fixedFlag$sample39 || !fixedFlag$sample57))
				st = new int[length$cpu_measured];
			if(!fixedFlag$sample36)
				initialStateDistribution = new double[noStates];
			cpu = new double[length$cpu_measured];
			mem = new double[length$cpu_measured];
			pageFaults = new double[length$cpu_measured];
			if(!fixedFlag$sample77)
				cpuMean = new double[noStates];
			if(!fixedFlag$sample95)
				memMean = new double[noStates];
			if(!fixedFlag$sample113)
				pageFaultsMean = new double[noStates];
			if(!fixedFlag$sample130)
				cpuVar = new double[noStates];
			if(!fixedFlag$sample147)
				memVar = new double[noStates];
			if(!fixedFlag$sample164)
				pageFaultsVar = new double[noStates];
			distribution$sample39 = new double[noStates];
			distribution$sample57 = new double[(length$cpu_measured - 1)][];
			for(int i$var50 = 1; i$var50 < length$cpu_measured; i$var50 += 1)
				distribution$sample57[(i$var50 - 1)] = new double[noStates];
			constrainedFlag$sample95 = new boolean[noStates];
			constrainedFlag$sample30 = new boolean[noStates];
			constrainedFlag$sample77 = new boolean[noStates];
			constrainedFlag$sample57 = new boolean[(length$cpu_measured - 1)];
			constrainedFlag$sample164 = new boolean[noStates];
			constrainedFlag$sample147 = new boolean[noStates];
			constrainedFlag$sample130 = new boolean[noStates];
			constrainedFlag$sample113 = new boolean[noStates];
			logProbability$sample57 = new double[(length$cpu_measured - 1)];
			logProbability$sample180 = new double[length$cpu_measured];
			logProbability$sample185 = new double[length$cpu_measured];
			logProbability$sample190 = new double[length$cpu_measured];
		}

		final double[] get$cpu() {
			return cpu;
		}

		final double[] get$cpuMean() {
			return cpuMean;
		}

		final void set$cpuMean(double[] cv$value, boolean allocated$) {
			cpuMean = cv$value;
			fixedProbFlag$sample77 = false;
			fixedProbFlag$sample180 = false;
		}

		final double[] get$cpuVar() {
			return cpuVar;
		}

		final void set$cpuVar(double[] cv$value, boolean allocated$) {
			cpuVar = cv$value;
			fixedProbFlag$sample130 = false;
			fixedProbFlag$sample180 = false;
		}

		final double[] get$cpu_measured() {
			return cpu_measured;
		}

		final void set$cpu_measured(double[] cv$value, boolean allocated$) {
			cpu_measured = cv$value;
		}

		final double[] get$distribution$sample39() {
			return distribution$sample39;
		}

		final void set$distribution$sample39(double[] cv$value, boolean allocated$) {
			distribution$sample39 = cv$value;
		}

		final double[][] get$distribution$sample57() {
			return distribution$sample57;
		}

		final void set$distribution$sample57(double[][] cv$value, boolean allocated$) {
			distribution$sample57 = cv$value;
		}

		final boolean get$fixedFlag$sample113() {
			return fixedFlag$sample113;
		}

		final void set$fixedFlag$sample113(boolean cv$value, boolean allocated$) {
			fixedFlag$sample113 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
					constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
			}
			fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
			fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		}

		final boolean get$fixedFlag$sample130() {
			return fixedFlag$sample130;
		}

		final void set$fixedFlag$sample130(boolean cv$value, boolean allocated$) {
			fixedFlag$sample130 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
					constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
			}
			fixedProbFlag$sample130 = (cv$value && fixedProbFlag$sample130);
			fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		}

		final boolean get$fixedFlag$sample147() {
			return fixedFlag$sample147;
		}

		final void set$fixedFlag$sample147(boolean cv$value, boolean allocated$) {
			fixedFlag$sample147 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
					constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
			}
			fixedProbFlag$sample147 = (cv$value && fixedProbFlag$sample147);
			fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		}

		final boolean get$fixedFlag$sample164() {
			return fixedFlag$sample164;
		}

		final void set$fixedFlag$sample164(boolean cv$value, boolean allocated$) {
			fixedFlag$sample164 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
					constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
			}
			fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
			fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		}

		final boolean get$fixedFlag$sample30() {
			return fixedFlag$sample30;
		}

		final void set$fixedFlag$sample30(boolean cv$value, boolean allocated$) {
			fixedFlag$sample30 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
					constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
			}
			fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
			fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		}

		final boolean get$fixedFlag$sample36() {
			return fixedFlag$sample36;
		}

		final void set$fixedFlag$sample36(boolean cv$value, boolean allocated$) {
			fixedFlag$sample36 = cv$value;
			constrainedFlag$sample36 = (cv$value || constrainedFlag$sample36);
			fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
			fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		}

		final boolean get$fixedFlag$sample39() {
			return fixedFlag$sample39;
		}

		final void set$fixedFlag$sample39(boolean cv$value, boolean allocated$) {
			fixedFlag$sample39 = cv$value;
			constrainedFlag$sample39 = (cv$value || constrainedFlag$sample39);
			fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
			fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
			fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
			fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
			fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		}

		final boolean get$fixedFlag$sample57() {
			return fixedFlag$sample57;
		}

		final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
			fixedFlag$sample57 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
					constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
			}
			fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
			fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
			fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
			fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		}

		final boolean get$fixedFlag$sample77() {
			return fixedFlag$sample77;
		}

		final void set$fixedFlag$sample77(boolean cv$value, boolean allocated$) {
			fixedFlag$sample77 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
					constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
			}
			fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
			fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		}

		final boolean get$fixedFlag$sample95() {
			return fixedFlag$sample95;
		}

		final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
			fixedFlag$sample95 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
					constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
			}
			fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
			fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		}

		final double[] get$initialStateDistribution() {
			return initialStateDistribution;
		}

		final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
			initialStateDistribution = cv$value;
			fixedProbFlag$sample36 = false;
			fixedProbFlag$sample39 = false;
		}

		final int get$length$cpu_measured() {
			return length$cpu_measured;
		}

		final void set$length$cpu_measured(int cv$value, boolean allocated$) {
			length$cpu_measured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$cpu() {
			return logProbability$cpu;
		}

		final double get$logProbability$cpuMean() {
			return logProbability$cpuMean;
		}

		final double get$logProbability$cpuVar() {
			return logProbability$cpuVar;
		}

		final double get$logProbability$initialStateDistribution() {
			return logProbability$initialStateDistribution;
		}

		final double get$logProbability$m() {
			return logProbability$m;
		}

		final double get$logProbability$mem() {
			return logProbability$mem;
		}

		final double get$logProbability$memMean() {
			return logProbability$memMean;
		}

		final double get$logProbability$memVar() {
			return logProbability$memVar;
		}

		final double get$logProbability$pageFaults() {
			return logProbability$pageFaults;
		}

		final double get$logProbability$pageFaultsMean() {
			return logProbability$pageFaultsMean;
		}

		final double get$logProbability$pageFaultsVar() {
			return logProbability$pageFaultsVar;
		}

		final double get$logProbability$st() {
			return logProbability$st;
		}

		final double[][] get$m() {
			return m;
		}

		final void set$m(double[][] cv$value, boolean allocated$) {
			m = cv$value;
			fixedProbFlag$sample30 = false;
			fixedProbFlag$sample57 = false;
		}

		final double[] get$mem() {
			return mem;
		}

		final double[] get$memMean() {
			return memMean;
		}

		final void set$memMean(double[] cv$value, boolean allocated$) {
			memMean = cv$value;
			fixedProbFlag$sample95 = false;
			fixedProbFlag$sample185 = false;
		}

		final double[] get$memVar() {
			return memVar;
		}

		final void set$memVar(double[] cv$value, boolean allocated$) {
			memVar = cv$value;
			fixedProbFlag$sample147 = false;
			fixedProbFlag$sample185 = false;
		}

		final double[] get$mem_measured() {
			return mem_measured;
		}

		final void set$mem_measured(double[] cv$value, boolean allocated$) {
			mem_measured = cv$value;
		}

		final int get$noStates() {
			return noStates;
		}

		final void set$noStates(int cv$value, boolean allocated$) {
			noStates = cv$value;
		}

		final double[] get$pageFaults() {
			return pageFaults;
		}

		final double[] get$pageFaultsMean() {
			return pageFaultsMean;
		}

		final void set$pageFaultsMean(double[] cv$value, boolean allocated$) {
			pageFaultsMean = cv$value;
			fixedProbFlag$sample113 = false;
			fixedProbFlag$sample190 = false;
		}

		final double[] get$pageFaultsVar() {
			return pageFaultsVar;
		}

		final void set$pageFaultsVar(double[] cv$value, boolean allocated$) {
			pageFaultsVar = cv$value;
			fixedProbFlag$sample164 = false;
			fixedProbFlag$sample190 = false;
		}

		final double[] get$pageFaults_measured() {
			return pageFaults_measured;
		}

		final void set$pageFaults_measured(double[] cv$value, boolean allocated$) {
			pageFaults_measured = cv$value;
		}

		final int get$samples() {
			return samples;
		}

		final int[] get$st() {
			return st;
		}

		final void set$st(int[] cv$value, boolean allocated$) {
			st = cv$value;
			fixedProbFlag$sample39 = false;
			fixedProbFlag$sample57 = false;
			fixedProbFlag$sample180 = false;
			fixedProbFlag$sample185 = false;
			fixedProbFlag$sample190 = false;
		}

		final double[] get$v() {
			return v;
		}
	}

    private final ComputedDoubleArrayInternal $cpu = new ComputedDoubleArrayInternal(this, "cpu", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$cpu(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable cpu because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$cpu(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing cpu of type double[] from the Sandwood model. */
    public final ComputedDoubleArray cpu = $cpu;

    private final ComputedDoubleArrayInternal $cpuMean = new ComputedDoubleArrayInternal(this, "cpuMean", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$cpuMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$cpuMean(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$cpuMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample77(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample77())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing cpuMean of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray cpuMean = $cpuMean;

    private final ComputedDoubleArrayInternal $cpuVar = new ComputedDoubleArrayInternal(this, "cpuVar", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$cpuVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$cpuVar(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$cpuVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample130(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample130())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing cpuVar of type double[] from the Sandwood model. */
    public final ComputedDoubleArray cpuVar = $cpuVar;

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
                state.set$fixedFlag$sample36(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample36())
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
                state.set$fixedFlag$sample30(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample30())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedDoubleArrayInternal $mem = new ComputedDoubleArrayInternal(this, "mem", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$mem(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable mem because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$mem(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing mem of type double[] from the Sandwood model. */
    public final ComputedDoubleArray mem = $mem;

    private final ComputedDoubleArrayInternal $memMean = new ComputedDoubleArrayInternal(this, "memMean", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$memMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$memMean(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$memMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample95(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample95())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing memMean of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray memMean = $memMean;

    private final ComputedDoubleArrayInternal $memVar = new ComputedDoubleArrayInternal(this, "memVar", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$memVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$memVar(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$memVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample147(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample147())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing memVar of type double[] from the Sandwood model. */
    public final ComputedDoubleArray memVar = $memVar;

    private final ComputedDoubleArrayInternal $pageFaults = new ComputedDoubleArrayInternal(this, "pageFaults", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$pageFaults(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable pageFaults because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$pageFaults(); }

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
	 * Computed variable representing pageFaults of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray pageFaults = $pageFaults;

    private final ComputedDoubleArrayInternal $pageFaultsMean = new ComputedDoubleArrayInternal(this, "pageFaultsMean", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$pageFaultsMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$pageFaultsMean(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$pageFaultsMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample113(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample113())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing pageFaultsMean of type double[] from the Sandwood
	 * model.
	 */
    public final ComputedDoubleArray pageFaultsMean = $pageFaultsMean;

    private final ComputedDoubleArrayInternal $pageFaultsVar = new ComputedDoubleArrayInternal(this, "pageFaultsVar", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$pageFaultsVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$pageFaultsVar(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$pageFaultsVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample164(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample164())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing pageFaultsVar of type double[] from the Sandwood
	 * model.
	 */
    public final ComputedDoubleArray pageFaultsVar = $pageFaultsVar;

    private final ComputedIntegerArrayInternal $st = new ComputedIntegerArrayInternal(this, "st", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$st(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$st(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$st(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample39(fixed, allocated);
                state.set$fixedFlag$sample57(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample39 = state.get$fixedFlag$sample39();
            boolean fixedFlag$sample57 = state.get$fixedFlag$sample57();
            if(fixedFlag$sample39 && fixedFlag$sample57)
                return Immutability.FIXED;
            else if(fixedFlag$sample39 || fixedFlag$sample57)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[] from the Sandwood model. */
    public final ComputedIntegerArray st = $st;

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

    private final ObservedDoubleArrayInternal $mem_measured = new ObservedDoubleArrayInternal(this, "mem_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$mem_measured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { state.set$mem_measured(value, allocated); }
    };

	/**
	 * Observed variable representing mem_measured of type double[] from the Sandwood
	 * model.
	 */
    public final ObservedDoubleArray mem_measured = $mem_measured;

    private final ObservedDoubleArrayInternal $pageFaults_measured = new ObservedDoubleArrayInternal(this, "pageFaults_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$pageFaults_measured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { state.set$pageFaults_measured(value, allocated); }
    };

	/**
	 * Observed variable representing pageFaults_measured of type double[] from the Sandwood
	 * model.
	 */
    public final ObservedDoubleArray pageFaults_measured = $pageFaults_measured;

    private final ObservedDoubleArrayShapeableInternal $cpu_measured = new ObservedDoubleArrayShapeableInternal(this, "cpu_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$cpu_measured();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            state.set$cpu_measured(value, allocated);
            state.set$length$cpu_measured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$cpu_measured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$cpu_measured();
        }
    };

	/**
	 * Observed variable representing cpu_measured of type double[] from the Sandwood
	 * model.
	 */
    public final ObservedDoubleArrayShapeable cpu_measured = $cpu_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$cpu, $cpuMean, $cpuVar, $initialStateDistribution, $m, $mem, $memMean, $memVar, $pageFaults, $pageFaultsMean, $pageFaultsVar, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMMMetrics() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("cpu", $cpu);
        $computedVariables.put("cpuMean", $cpuMean);
        $computedVariables.put("cpuVar", $cpuVar);
        $computedVariables.put("initialStateDistribution", $initialStateDistribution);
        $computedVariables.put("m", $m);
        $computedVariables.put("mem", $mem);
        $computedVariables.put("memMean", $memMean);
        $computedVariables.put("memVar", $memVar);
        $computedVariables.put("pageFaults", $pageFaults);
        $computedVariables.put("pageFaultsMean", $pageFaultsMean);
        $computedVariables.put("pageFaultsVar", $pageFaultsVar);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("noStates", $noStates);

        //Observed scalar fields
        $regularObservedValues.put("mem_measured", $mem_measured);
        $regularObservedValues.put("pageFaults_measured", $pageFaults_measured);

        //Observed array fields
        $shapedObservedValues.put("cpu_measured", $cpu_measured);

        HMMMetrics$SingleThreadCPU core = new HMMMetrics$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param cpu_measuredShape An integer array describing the shape of variable cpu_measured
	 *                          to use in the model when generating results.
	 * @param noStates The value to set noStates to.
	 */
    public HMMMetrics(int cpu_measuredShape, int noStates) {
        this();
        this.$noStates.setValue(noStates);
        this.$cpu_measured.setShape(cpu_measuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param cpu_measured The value to set cpu_measured to.
	 * @param mem_measured The value to set mem_measured to
	 * @param pageFaults_measured The value to set pageFaults_measured to
	 * @param noStates The value to set noStates to
	 */
    public HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {
        this();
        this.cpu_measured.setValue(cpu_measured);
        this.mem_measured.setValue(mem_measured);
        this.pageFaults_measured.setValue(pageFaults_measured);
        this.noStates.setValue(noStates);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMMMetrics$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMMMetrics$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input cpu_measured */
        public final int cpu_measuredShape;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param cpu_measuredShape An integer array describing the shape of variable cpu_measured
		 *                          to use in the model when generating results.
		 * @param noStates The value to set noStates to.
		 */
        public InferValueInputs(int cpu_measuredShape, int noStates) {
            this.noStates = noStates;
            this.cpu_measuredShape = cpu_measuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input cpu_measured */
        public final double[] cpu_measured;
		/** Field holding the value of model input mem_measured */
        public final double[] mem_measured;
		/** Field holding the value of model input pageFaults_measured */
        public final double[] pageFaults_measured;
		/** Field holding the value of model input noStates */
        public final int noStates;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param cpu_measured The value to set cpu_measured to.
		 * @param mem_measured The value to set mem_measured to.
		 * @param pageFaults_measured The value to set pageFaults_measured to.
		 * @param noStates The value to set noStates to.
		 */
        public AllInputs(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {
            this.cpu_measured = cpu_measured;
            this.mem_measured = mem_measured;
            this.pageFaults_measured = pageFaults_measured;
            this.noStates = noStates;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of cpu after a convention execution step. */
        public final double[] cpu;
		/** Field holding the value of cpuMean after a convention execution step. */
        public final double[] cpuMean;
		/** Field holding the value of cpuVar after a convention execution step. */
        public final double[] cpuVar;
		/**
		 * Field holding the value of initialStateDistribution after a convention execution
		 * step.
		 */
        public final double[] initialStateDistribution;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of mem after a convention execution step. */
        public final double[] mem;
		/** Field holding the value of memMean after a convention execution step. */
        public final double[] memMean;
		/** Field holding the value of memVar after a convention execution step. */
        public final double[] memVar;
		/** Field holding the value of pageFaults after a convention execution step. */
        public final double[] pageFaults;
		/** Field holding the value of pageFaultsMean after a convention execution step. */
        public final double[] pageFaultsMean;
		/** Field holding the value of pageFaultsVar after a convention execution step. */
        public final double[] pageFaultsVar;
		/** Field holding the value of st after a convention execution step. */
        public final int[] st;

        InferredValueOutputs(HMMMetrics system$model) {
            this.cpu = system$model.cpu.getSamples()[0];
            this.cpuMean = system$model.cpuMean.getSamples()[0];
            this.cpuVar = system$model.cpuVar.getSamples()[0];
            this.initialStateDistribution = system$model.initialStateDistribution.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.mem = system$model.mem.getSamples()[0];
            this.memMean = system$model.memMean.getSamples()[0];
            this.memVar = system$model.memVar.getSamples()[0];
            this.pageFaults = system$model.pageFaults.getSamples()[0];
            this.pageFaultsMean = system$model.pageFaultsMean.getSamples()[0];
            this.pageFaultsVar = system$model.pageFaultsVar.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable cpu */
        public final double cpu;
		/** Field holding the log probability of computed variable cpuMean */
        public final double cpuMean;
		/** Field holding the log probability of computed variable cpuVar */
        public final double cpuVar;
		/** Field holding the log probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable mem */
        public final double mem;
		/** Field holding the log probability of computed variable memMean */
        public final double memMean;
		/** Field holding the log probability of computed variable memVar */
        public final double memVar;
		/** Field holding the log probability of computed variable pageFaults */
        public final double pageFaults;
		/** Field holding the log probability of computed variable pageFaultsMean */
        public final double pageFaultsMean;
		/** Field holding the log probability of computed variable pageFaultsVar */
        public final double pageFaultsVar;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMMetrics system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.cpu = system$model.cpu.getLogProbability();
            this.cpuMean = system$model.cpuMean.getLogProbability();
            this.cpuVar = system$model.cpuVar.getLogProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.mem = system$model.mem.getLogProbability();
            this.memMean = system$model.memMean.getLogProbability();
            this.memVar = system$model.memVar.getLogProbability();
            this.pageFaults = system$model.pageFaults.getLogProbability();
            this.pageFaultsMean = system$model.pageFaultsMean.getLogProbability();
            this.pageFaultsVar = system$model.pageFaultsVar.getLogProbability();
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
		/** Field holding the probability of computed variable cpu */
        public final double cpu;
		/** Field holding the probability of computed variable cpuMean */
        public final double cpuMean;
		/** Field holding the probability of computed variable cpuVar */
        public final double cpuVar;
		/** Field holding the probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable mem */
        public final double mem;
		/** Field holding the probability of computed variable memMean */
        public final double memMean;
		/** Field holding the probability of computed variable memVar */
        public final double memVar;
		/** Field holding the probability of computed variable pageFaults */
        public final double pageFaults;
		/** Field holding the probability of computed variable pageFaultsMean */
        public final double pageFaultsMean;
		/** Field holding the probability of computed variable pageFaultsVar */
        public final double pageFaultsVar;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMMetrics system$model) {
            this.$modelProbability = system$model.getProbability();
            this.cpu = system$model.cpu.getProbability();
            this.cpuMean = system$model.cpuMean.getProbability();
            this.cpuVar = system$model.cpuVar.getProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getProbability();
            this.m = system$model.m.getProbability();
            this.mem = system$model.mem.getProbability();
            this.memMean = system$model.memMean.getProbability();
            this.memVar = system$model.memVar.getProbability();
            this.pageFaults = system$model.pageFaults.getProbability();
            this.pageFaultsMean = system$model.pageFaultsMean.getProbability();
            this.pageFaultsVar = system$model.pageFaultsVar.getProbability();
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
		/** Field holding the MAP or Sample value of cpuMean after an infer model call. */
        public final double[][] cpuMean;
		/** Field holding the MAP or Sample value of cpuVar after an infer model call. */
        public final double[][] cpuVar;
		/**
		 * Field holding the MAP or Sample value of initialStateDistribution after an infer
		 * model call.
		 */
        public final double[][] initialStateDistribution;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of memMean after an infer model call. */
        public final double[][] memMean;
		/** Field holding the MAP or Sample value of memVar after an infer model call. */
        public final double[][] memVar;
		/**
		 * Field holding the MAP or Sample value of pageFaultsMean after an infer model call.
		 */
        public final double[][] pageFaultsMean;
		/**
		 * Field holding the MAP or Sample value of pageFaultsVar after an infer model call.
		 */
        public final double[][] pageFaultsVar;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][] st;

        InferredModelOutputs(HMMMetrics system$model) {
            this.cpuMean = system$model.getInferredValue(system$model.$cpuMean);
            this.cpuVar = system$model.getInferredValue(system$model.$cpuVar);
            this.initialStateDistribution = system$model.getInferredValue(system$model.$initialStateDistribution);
            this.m = system$model.getInferredValue(system$model.$m);
            this.memMean = system$model.getInferredValue(system$model.$memMean);
            this.memVar = system$model.getInferredValue(system$model.$memVar);
            this.pageFaultsMean = system$model.getInferredValue(system$model.$pageFaultsMean);
            this.pageFaultsVar = system$model.getInferredValue(system$model.$pageFaultsVar);
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
        this.$cpu_measured.setShape(inputs.cpu_measuredShape);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}