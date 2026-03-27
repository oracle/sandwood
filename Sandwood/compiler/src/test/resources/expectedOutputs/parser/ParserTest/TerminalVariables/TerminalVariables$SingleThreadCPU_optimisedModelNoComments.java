package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements TerminalVariables$CoreInterface {
	private double[][][][][] a;
	private int c1;
	private int c10;
	private int c11;
	private int c12;
	private int c2;
	private int c3;
	private int c4;
	private int c5;
	private int c6;
	private int c7;
	private int c8;
	private int c9;
	private double[][] conditionals;
	private boolean constrainedFlag$sample47 = true;
	private boolean constrainedFlag$sample52 = true;
	private boolean constrainedFlag$sample55 = true;
	private boolean constrainedFlag$sample57 = true;
	private boolean constrainedFlag$sample62 = true;
	private boolean constrainedFlag$sample67 = true;
	private boolean constrainedFlag$sample72 = true;
	private double[] cv$var45$stateProbabilityGlobal;
	private double[] cv$var50$stateProbabilityGlobal;
	private double[] cv$var53$stateProbabilityGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var60$stateProbabilityGlobal;
	private double[] cv$var65$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private int evidence;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample60 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedFlag$sample636 = false;
	private boolean fixedFlag$sample65 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample70 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample636 = false;
	private boolean fixedProbFlag$sample65 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample70 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean fixedProbFlag$sample75 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c1;
	private double logProbability$c10;
	private double logProbability$c11;
	private double logProbability$c12;
	private double logProbability$c2;
	private double logProbability$c3;
	private double logProbability$c4;
	private double logProbability$c5;
	private double logProbability$c6;
	private double logProbability$c7;
	private double logProbability$c8;
	private double logProbability$c9;
	private double logProbability$terminalVariable;
	private double[] priors;
	private boolean system$gibbsForward = true;
	private int terminalVariable;

	public TerminalVariables$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][][][][] get$a() {
		return a;
	}

	@Override
	public final int get$c1() {
		return c1;
	}

	@Override
	public final void set$c1(int cv$value, boolean allocated$) {
		c1 = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample636 = false;
	}

	@Override
	public final int get$c10() {
		return c10;
	}

	@Override
	public final void set$c10(int cv$value, boolean allocated$) {
		c10 = cv$value;
		fixedProbFlag$sample70 = false;
	}

	@Override
	public final int get$c11() {
		return c11;
	}

	@Override
	public final void set$c11(int cv$value, boolean allocated$) {
		c11 = cv$value;
		fixedProbFlag$sample72 = false;
		fixedProbFlag$sample75 = false;
	}

	@Override
	public final int get$c12() {
		return c12;
	}

	@Override
	public final void set$c12(int cv$value, boolean allocated$) {
		c12 = cv$value;
		fixedProbFlag$sample75 = false;
	}

	@Override
	public final int get$c2() {
		return c2;
	}

	@Override
	public final int get$c3() {
		return c3;
	}

	@Override
	public final void set$c3(int cv$value, boolean allocated$) {
		c3 = cv$value;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$c4() {
		return c4;
	}

	@Override
	public final void set$c4(int cv$value, boolean allocated$) {
		c4 = cv$value;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample636 = false;
	}

	@Override
	public final int get$c5() {
		return c5;
	}

	@Override
	public final void set$c5(int cv$value, boolean allocated$) {
		c5 = cv$value;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample60 = false;
		fixedProbFlag$sample636 = false;
	}

	@Override
	public final int get$c6() {
		return c6;
	}

	@Override
	public final void set$c6(int cv$value, boolean allocated$) {
		c6 = cv$value;
		fixedProbFlag$sample60 = false;
	}

	@Override
	public final int get$c7() {
		return c7;
	}

	@Override
	public final void set$c7(int cv$value, boolean allocated$) {
		c7 = cv$value;
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample65 = false;
	}

	@Override
	public final int get$c8() {
		return c8;
	}

	@Override
	public final void set$c8(int cv$value, boolean allocated$) {
		c8 = cv$value;
		fixedProbFlag$sample65 = false;
	}

	@Override
	public final int get$c9() {
		return c9;
	}

	@Override
	public final void set$c9(int cv$value, boolean allocated$) {
		c9 = cv$value;
		fixedProbFlag$sample67 = false;
		fixedProbFlag$sample70 = false;
		fixedProbFlag$sample636 = false;
	}

	@Override
	public final double[][] get$conditionals() {
		return conditionals;
	}

	@Override
	public final int get$evidence() {
		return evidence;
	}

	@Override
	public final void set$evidence(int cv$value, boolean allocated$) {
		evidence = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
		fixedFlag$sample47 = cv$value;
		constrainedFlag$sample47 = (cv$value || constrainedFlag$sample47);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value, boolean allocated$) {
		fixedFlag$sample52 = cv$value;
		constrainedFlag$sample52 = (cv$value || constrainedFlag$sample52);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value, boolean allocated$) {
		fixedFlag$sample55 = cv$value;
		constrainedFlag$sample55 = (cv$value || constrainedFlag$sample55);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		fixedFlag$sample57 = cv$value;
		constrainedFlag$sample57 = (cv$value || constrainedFlag$sample57);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	@Override
	public final boolean get$fixedFlag$sample60() {
		return fixedFlag$sample60;
	}

	@Override
	public final void set$fixedFlag$sample60(boolean cv$value, boolean allocated$) {
		fixedFlag$sample60 = cv$value;
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value, boolean allocated$) {
		fixedFlag$sample62 = cv$value;
		constrainedFlag$sample62 = (cv$value || constrainedFlag$sample62);
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	@Override
	public final boolean get$fixedFlag$sample636() {
		return fixedFlag$sample636;
	}

	@Override
	public final void set$fixedFlag$sample636(boolean cv$value, boolean allocated$) {
		fixedFlag$sample636 = cv$value;
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	@Override
	public final void set$fixedFlag$sample65(boolean cv$value, boolean allocated$) {
		fixedFlag$sample65 = cv$value;
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value, boolean allocated$) {
		fixedFlag$sample67 = cv$value;
		constrainedFlag$sample67 = (cv$value || constrainedFlag$sample67);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	@Override
	public final boolean get$fixedFlag$sample70() {
		return fixedFlag$sample70;
	}

	@Override
	public final void set$fixedFlag$sample70(boolean cv$value, boolean allocated$) {
		fixedFlag$sample70 = cv$value;
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	@Override
	public final void set$fixedFlag$sample72(boolean cv$value, boolean allocated$) {
		fixedFlag$sample72 = cv$value;
		constrainedFlag$sample72 = (cv$value || constrainedFlag$sample72);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value, boolean allocated$) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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
	public final double get$logProbability$c1() {
		return logProbability$c1;
	}

	@Override
	public final double get$logProbability$c10() {
		return logProbability$c10;
	}

	@Override
	public final double get$logProbability$c11() {
		return logProbability$c11;
	}

	@Override
	public final double get$logProbability$c12() {
		return logProbability$c12;
	}

	@Override
	public final double get$logProbability$c2() {
		return logProbability$c2;
	}

	@Override
	public final double get$logProbability$c3() {
		return logProbability$c3;
	}

	@Override
	public final double get$logProbability$c4() {
		return logProbability$c4;
	}

	@Override
	public final double get$logProbability$c5() {
		return logProbability$c5;
	}

	@Override
	public final double get$logProbability$c6() {
		return logProbability$c6;
	}

	@Override
	public final double get$logProbability$c7() {
		return logProbability$c7;
	}

	@Override
	public final double get$logProbability$c8() {
		return logProbability$c8;
	}

	@Override
	public final double get$logProbability$c9() {
		return logProbability$c9;
	}

	@Override
	public final double get$logProbability$terminalVariable() {
		return logProbability$terminalVariable;
	}

	@Override
	public final double[] get$priors() {
		return priors;
	}

	@Override
	public final int get$terminalVariable() {
		return terminalVariable;
	}

	@Override
	public final void set$terminalVariable(int cv$value, boolean allocated$) {
		terminalVariable = cv$value;
		fixedProbFlag$sample636 = false;
	}

	private final void drawValueSample47() {
		c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample52() {
		c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample55() {
		int lengthCV$conditionals$53_3 = -1;
		if((0 == c3))
			lengthCV$conditionals$53_3 = 2;
		if((1 == c3))
			lengthCV$conditionals$53_3 = 2;
		c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_3);
	}

	private final void drawValueSample57() {
		c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample60() {
		int lengthCV$conditionals$58_1 = -1;
		if((0 == c5))
			lengthCV$conditionals$58_1 = 2;
		if((1 == c5))
			lengthCV$conditionals$58_1 = 2;
		c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_1);
	}

	private final void drawValueSample62() {
		c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample636() {
		int lengthCV$var601$634_4 = -1;
		if((0 == c5)) {
			if((0 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			if((1 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		if((1 == c5)) {
			if((0 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			if((1 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_4);
	}

	private final void drawValueSample65() {
		int lengthCV$conditionals$63_1 = -1;
		if((0 == c7))
			lengthCV$conditionals$63_1 = 2;
		if((1 == c7))
			lengthCV$conditionals$63_1 = 2;
		c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_1);
	}

	private final void drawValueSample67() {
		c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample70() {
		int lengthCV$conditionals$68_1 = -1;
		if((0 == c9))
			lengthCV$conditionals$68_1 = 2;
		if((1 == c9))
			lengthCV$conditionals$68_1 = 2;
		c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_1);
	}

	private final void drawValueSample72() {
		c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
	}

	private final void drawValueSample75() {
		int lengthCV$conditionals$73_1 = -1;
		if((0 == c11))
			lengthCV$conditionals$73_1 = 2;
		if((1 == c11))
			lengthCV$conditionals$73_1 = 2;
		c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_1);
	}

	private final void inferSample47() {
		constrainedFlag$sample47 = false;
		{
			c1 = 0;
			constrainedFlag$sample47 = true;
			double[] var46 = conditionals[0];
			double cv$accumulatedProbabilities = ((((((0.0 <= c2) && (c2 < 2)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY));
			if(fixedFlag$sample636) {
				constrainedFlag$sample47 = true;
				double[] var602 = a[c5][c9][0][c4];
				int lengthCV$var601$634_0 = -1;
				if((0 == c5)) {
					if((0 == c9)) {
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
					if((1 == c9)) {
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				if((1 == c5)) {
					if((0 == c9)) {
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
					if((1 == c9)) {
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c1 = 1;
		constrainedFlag$sample47 = true;
		double[] var46 = conditionals[1];
		double cv$accumulatedProbabilities = ((((((0.0 <= c2) && (c2 < 2)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY));
		if(fixedFlag$sample636) {
			constrainedFlag$sample47 = true;
			double[] var602 = a[c5][c9][1][c4];
			int lengthCV$var601$634_0 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
				if((1 == c9)) {
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
				if((1 == c9)) {
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var45$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var45$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var45$stateProbabilityGlobal[0] = 0.5;
			cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var45$stateProbabilityGlobal[0] = Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			cv$var45$stateProbabilityGlobal[1] = Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		c1 = DistributionSampling.sampleCategorical(RNG$, cv$var45$stateProbabilityGlobal, 2);
	}

	private final void inferSample52() {
		constrainedFlag$sample52 = false;
		{
			c3 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample55 || constrainedFlag$sample55)) {
				constrainedFlag$sample52 = true;
				double[] var51 = conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= c4) && (c4 < 2)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c3 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		if((fixedFlag$sample55 || constrainedFlag$sample55)) {
			constrainedFlag$sample52 = true;
			double[] var51 = conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= c4) && (c4 < 2)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample52) {
			double cv$logSum;
			double cv$lseMax = cv$var50$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var50$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var50$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var50$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var50$stateProbabilityGlobal[0] = 0.5;
				cv$var50$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var50$stateProbabilityGlobal[0] = Math.exp((cv$var50$stateProbabilityGlobal[0] - cv$logSum));
				cv$var50$stateProbabilityGlobal[1] = Math.exp((cv$var50$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var50$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c3 = DistributionSampling.sampleCategorical(RNG$, cv$var50$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample55() {
		constrainedFlag$sample55 = false;
		int lengthCV$conditionals$53_1 = -1;
		if((0 == c3))
			lengthCV$conditionals$53_1 = 2;
		if((1 == c3))
			lengthCV$conditionals$53_1 = 2;
		int cv$numStates = Math.max(0, lengthCV$conditionals$53_1);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			c4 = cv$valuePos;
			double[] var51 = conditionals[c3];
			int lengthCV$conditionals$53_2 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_2 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_2 = 2;
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$conditionals$53_2) && (0 < lengthCV$conditionals$53_2)) && (0.0 <= var51[cv$valuePos])) && (var51[cv$valuePos] <= 1.0))?Math.log(var51[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample636) {
				constrainedFlag$sample55 = true;
				double[] var602 = a[c5][c9][c1][cv$valuePos];
				int lengthCV$var601$634_1 = -1;
				if((0 == c5)) {
					if((0 == c9)) {
						if((0 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						if((1 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					if((1 == c9)) {
						if((0 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						if((1 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				if((1 == c5)) {
					if((0 == c9)) {
						if((0 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						if((1 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					if((1 == c9)) {
						if((0 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						if((1 == c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_1)) && (0 < lengthCV$var601$634_1)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var53$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample55) {
			double cv$logSum;
			double cv$lseMax = cv$var53$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var53$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var53$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var53$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var53$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var53$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c4 = DistributionSampling.sampleCategorical(RNG$, cv$var53$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample57() {
		constrainedFlag$sample57 = false;
		{
			c5 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample60) {
				constrainedFlag$sample57 = true;
				double[] var56 = conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= c6) && (c6 < 2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(fixedFlag$sample636) {
				constrainedFlag$sample57 = true;
				double[] var602 = a[0][c9][c1][c4];
				int lengthCV$var601$634_2 = -1;
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c5 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample60) {
			constrainedFlag$sample57 = true;
			double[] var56 = conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= c6) && (c6 < 2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if(fixedFlag$sample636) {
			constrainedFlag$sample57 = true;
			double[] var602 = a[1][c9][c1][c4];
			int lengthCV$var601$634_2 = -1;
			if((0 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			if((1 == c9)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample57) {
			double cv$logSum;
			double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var55$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var55$stateProbabilityGlobal[0] = 0.5;
				cv$var55$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var55$stateProbabilityGlobal[0] = Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$logSum));
				cv$var55$stateProbabilityGlobal[1] = Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c5 = DistributionSampling.sampleCategorical(RNG$, cv$var55$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample62() {
		constrainedFlag$sample62 = false;
		{
			c7 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample65) {
				constrainedFlag$sample62 = true;
				double[] var61 = conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= c8) && (c8 < 2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var60$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c7 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample65) {
			constrainedFlag$sample62 = true;
			double[] var61 = conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= c8) && (c8 < 2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var60$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample62) {
			double cv$logSum;
			double cv$lseMax = cv$var60$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var60$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var60$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var60$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var60$stateProbabilityGlobal[0] = 0.5;
				cv$var60$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var60$stateProbabilityGlobal[0] = Math.exp((cv$var60$stateProbabilityGlobal[0] - cv$logSum));
				cv$var60$stateProbabilityGlobal[1] = Math.exp((cv$var60$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var60$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var60$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c7 = DistributionSampling.sampleCategorical(RNG$, cv$var60$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample67() {
		constrainedFlag$sample67 = false;
		{
			c9 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample70) {
				constrainedFlag$sample67 = true;
				double[] var66 = conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= c10) && (c10 < 2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(fixedFlag$sample636) {
				constrainedFlag$sample67 = true;
				double[] var602 = a[c5][0][c1][c4];
				int lengthCV$var601$634_3 = -1;
				if((0 == c5)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				if((1 == c5)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var65$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c9 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample70) {
			constrainedFlag$sample67 = true;
			double[] var66 = conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= c10) && (c10 < 2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if(fixedFlag$sample636) {
			constrainedFlag$sample67 = true;
			double[] var602 = a[c5][1][c1][c4];
			int lengthCV$var601$634_3 = -1;
			if((0 == c5)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			if((1 == c5)) {
				if((0 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
				if((1 == c1)) {
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var65$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample67) {
			double cv$logSum;
			double cv$lseMax = cv$var65$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var65$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var65$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var65$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var65$stateProbabilityGlobal[0] = 0.5;
				cv$var65$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var65$stateProbabilityGlobal[0] = Math.exp((cv$var65$stateProbabilityGlobal[0] - cv$logSum));
				cv$var65$stateProbabilityGlobal[1] = Math.exp((cv$var65$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var65$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var65$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c9 = DistributionSampling.sampleCategorical(RNG$, cv$var65$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample72() {
		constrainedFlag$sample72 = false;
		{
			c11 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample75) {
				constrainedFlag$sample72 = true;
				double[] var71 = conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= c12) && (c12 < 2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var70$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		c11 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		if(fixedFlag$sample75) {
			constrainedFlag$sample72 = true;
			double[] var71 = conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= c12) && (c12 < 2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var70$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample72) {
			double cv$logSum;
			double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var70$stateProbabilityGlobal[0] = 0.5;
				cv$var70$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var70$stateProbabilityGlobal[0] = Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$logSum));
				cv$var70$stateProbabilityGlobal[1] = Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			c11 = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, 2);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$distributionAccumulator = (((((0.0 <= c1) && (c1 < 2)) && (0.0 <= priors[c1])) && (priors[c1] <= 1.0))?Math.log(priors[c1]):Double.NEGATIVE_INFINITY);
			logProbability$c1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c1);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c1);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double[] var46 = conditionals[c1];
			int lengthCV$conditionals$48_1 = -1;
			if((0 == c1))
				lengthCV$conditionals$48_1 = 2;
			if((1 == c1))
				lengthCV$conditionals$48_1 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c2) && (c2 < lengthCV$conditionals$48_1)) && (0 < lengthCV$conditionals$48_1)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY);
			logProbability$c2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample50 = fixedFlag$sample47;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c2);
			logProbability$$evidence = (logProbability$$evidence + logProbability$c2);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$distributionAccumulator = (((((0.0 <= c3) && (c3 < 2)) && (0.0 <= priors[c3])) && (priors[c3] <= 1.0))?Math.log(priors[c3]):Double.NEGATIVE_INFINITY);
			logProbability$c3 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c3);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c3);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double[] var51 = conditionals[c3];
			int lengthCV$conditionals$53_4 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_4 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_4 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c4) && (c4 < lengthCV$conditionals$53_4)) && (0 < lengthCV$conditionals$53_4)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY);
			logProbability$c4 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample52);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c4);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c4);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$distributionAccumulator = (((((0.0 <= c5) && (c5 < 2)) && (0.0 <= priors[c5])) && (priors[c5] <= 1.0))?Math.log(priors[c5]):Double.NEGATIVE_INFINITY);
			logProbability$c5 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c5);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c5);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!fixedProbFlag$sample60) {
			double[] var56 = conditionals[c5];
			int lengthCV$conditionals$58_2 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_2 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_2 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c6) && (c6 < lengthCV$conditionals$58_2)) && (0 < lengthCV$conditionals$58_2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY);
			logProbability$c6 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample60 = (fixedFlag$sample60 && fixedFlag$sample57);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c6);
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c6);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$distributionAccumulator = (((((0.0 <= c7) && (c7 < 2)) && (0.0 <= priors[c7])) && (priors[c7] <= 1.0))?Math.log(priors[c7]):Double.NEGATIVE_INFINITY);
			logProbability$c7 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample62 = fixedFlag$sample62;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c7);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c7);
		}
	}

	private final void logProbabilityValue$sample636() {
		if(!fixedProbFlag$sample636) {
			double[] var602 = a[c5][c9][c1][c4];
			int lengthCV$var601$634_5 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			double cv$distributionAccumulator = ((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_5)) && (0 < lengthCV$var601$634_5)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY);
			logProbability$terminalVariable = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample636)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample636 = ((((fixedFlag$sample636 && fixedFlag$sample47) && fixedFlag$sample55) && fixedFlag$sample57) && fixedFlag$sample67);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$terminalVariable);
			if(fixedFlag$sample636)
				logProbability$$evidence = (logProbability$$evidence + logProbability$terminalVariable);
		}
	}

	private final void logProbabilityValue$sample65() {
		if(!fixedProbFlag$sample65) {
			double[] var61 = conditionals[c7];
			int lengthCV$conditionals$63_2 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_2 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_2 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c8) && (c8 < lengthCV$conditionals$63_2)) && (0 < lengthCV$conditionals$63_2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY);
			logProbability$c8 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedFlag$sample62);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c8);
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c8);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$distributionAccumulator = (((((0.0 <= c9) && (c9 < 2)) && (0.0 <= priors[c9])) && (priors[c9] <= 1.0))?Math.log(priors[c9]):Double.NEGATIVE_INFINITY);
			logProbability$c9 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample67 = fixedFlag$sample67;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c9);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c9);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!fixedProbFlag$sample70) {
			double[] var66 = conditionals[c9];
			int lengthCV$conditionals$68_2 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_2 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_2 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c10) && (c10 < lengthCV$conditionals$68_2)) && (0 < lengthCV$conditionals$68_2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY);
			logProbability$c10 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample70)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample70 = (fixedFlag$sample70 && fixedFlag$sample67);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c10);
			if(fixedFlag$sample70)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c10);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$distributionAccumulator = (((((0.0 <= c11) && (c11 < 2)) && (0.0 <= priors[c11])) && (priors[c11] <= 1.0))?Math.log(priors[c11]):Double.NEGATIVE_INFINITY);
			logProbability$c11 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample72 = fixedFlag$sample72;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c11);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c11);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double[] var71 = conditionals[c11];
			int lengthCV$conditionals$73_2 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_2 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_2 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= c12) && (c12 < lengthCV$conditionals$73_2)) && (0 < lengthCV$conditionals$73_2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY);
			logProbability$c12 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample72);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$c12);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + logProbability$c12);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var45$stateProbabilityGlobal = new double[2];
		cv$var50$stateProbabilityGlobal = new double[2];
		cv$var53$stateProbabilityGlobal = new double[2];
		cv$var55$stateProbabilityGlobal = new double[2];
		cv$var60$stateProbabilityGlobal = new double[2];
		cv$var65$stateProbabilityGlobal = new double[2];
		cv$var70$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		priors = new double[2];
		conditionals = new double[2][];
		conditionals[0] = new double[2];
		conditionals[1] = new double[2];
		a = new double[2][][][][];
		double[][][][] subarray$0 = new double[2][][][];
		a[0] = subarray$0;
		double[][][] subarray$1 = new double[2][][];
		subarray$0[0] = subarray$1;
		double[][] subarray$2 = new double[2][];
		subarray$1[0] = subarray$2;
		subarray$2[0] = new double[5];
		subarray$2[1] = new double[5];
		double[][] subarray$3 = new double[2][];
		subarray$1[1] = subarray$3;
		subarray$3[0] = new double[5];
		subarray$3[1] = new double[5];
		double[][][] subarray$4 = new double[2][][];
		subarray$0[1] = subarray$4;
		double[][] subarray$5 = new double[2][];
		subarray$4[0] = subarray$5;
		subarray$5[0] = new double[5];
		subarray$5[1] = new double[5];
		double[][] subarray$6 = new double[2][];
		subarray$4[1] = subarray$6;
		subarray$6[0] = new double[5];
		subarray$6[1] = new double[5];
		double[][][][] subarray$7 = new double[2][][][];
		a[1] = subarray$7;
		double[][][] subarray$8 = new double[2][][];
		subarray$7[0] = subarray$8;
		double[][] subarray$9 = new double[2][];
		subarray$8[0] = subarray$9;
		subarray$9[0] = new double[5];
		subarray$9[1] = new double[5];
		double[][] subarray$10 = new double[2][];
		subarray$8[1] = subarray$10;
		subarray$10[0] = new double[5];
		subarray$10[1] = new double[5];
		double[][][] subarray$11 = new double[2][][];
		subarray$7[1] = subarray$11;
		double[][] subarray$12 = new double[2][];
		subarray$11[0] = subarray$12;
		subarray$12[0] = new double[5];
		subarray$12[1] = new double[5];
		double[][] subarray$13 = new double[2][];
		subarray$11[1] = subarray$13;
		subarray$13[0] = new double[5];
		subarray$13[1] = new double[5];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		int lengthCV$conditionals$48_2 = -1;
		if((0 == c1))
			lengthCV$conditionals$48_2 = 2;
		if((1 == c1))
			lengthCV$conditionals$48_2 = 2;
		c2 = DistributionSampling.sampleCategorical(RNG$, conditionals[c1], lengthCV$conditionals$48_2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample55) {
			int lengthCV$conditionals$53_5 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_5 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_5 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_5);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample60) {
			int lengthCV$conditionals$58_3 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_3 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_3 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_3);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample65) {
			int lengthCV$conditionals$63_3 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_3 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_3 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_3);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample70) {
			int lengthCV$conditionals$68_3 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_3 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_3 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_3);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample75) {
			int lengthCV$conditionals$73_3 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_3 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_3 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_3);
		}
		if(!fixedFlag$sample636) {
			int lengthCV$var601$634_6 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_6);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample55) {
			int lengthCV$conditionals$53_9 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_9 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_9 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_9);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample60) {
			int lengthCV$conditionals$58_7 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_7 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_7 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_7);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample65) {
			int lengthCV$conditionals$63_7 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_7 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_7 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_7);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample70) {
			int lengthCV$conditionals$68_7 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_7 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_7 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_7);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample75) {
			int lengthCV$conditionals$73_7 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_7 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_7 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_7);
		}
		if(!fixedFlag$sample636) {
			int lengthCV$var601$634_10 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_10);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		int lengthCV$conditionals$48_3 = -1;
		if((0 == c1))
			lengthCV$conditionals$48_3 = 2;
		if((1 == c1))
			lengthCV$conditionals$48_3 = 2;
		c2 = DistributionSampling.sampleCategorical(RNG$, conditionals[c1], lengthCV$conditionals$48_3);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample55) {
			int lengthCV$conditionals$53_6 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_6 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_6 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_6);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample60) {
			int lengthCV$conditionals$58_4 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_4 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_4 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_4);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample65) {
			int lengthCV$conditionals$63_4 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_4 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_4 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_4);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample70) {
			int lengthCV$conditionals$68_4 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_4 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_4 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_4);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample75) {
			int lengthCV$conditionals$73_4 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_4 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_4 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_4);
		}
		if(!fixedFlag$sample636) {
			int lengthCV$var601$634_7 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_7);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample55) {
			int lengthCV$conditionals$53_7 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_7 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_7 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_7);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample60) {
			int lengthCV$conditionals$58_5 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_5 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_5 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_5);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample65) {
			int lengthCV$conditionals$63_5 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_5 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_5 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_5);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample70) {
			int lengthCV$conditionals$68_5 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_5 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_5 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_5);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample75) {
			int lengthCV$conditionals$73_5 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_5 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_5 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_5);
		}
		if(!fixedFlag$sample636) {
			int lengthCV$var601$634_8 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_8);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample55) {
			int lengthCV$conditionals$53_8 = -1;
			if((0 == c3))
				lengthCV$conditionals$53_8 = 2;
			if((1 == c3))
				lengthCV$conditionals$53_8 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_8);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample60) {
			int lengthCV$conditionals$58_6 = -1;
			if((0 == c5))
				lengthCV$conditionals$58_6 = 2;
			if((1 == c5))
				lengthCV$conditionals$58_6 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_6);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample65) {
			int lengthCV$conditionals$63_6 = -1;
			if((0 == c7))
				lengthCV$conditionals$63_6 = 2;
			if((1 == c7))
				lengthCV$conditionals$63_6 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_6);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample70) {
			int lengthCV$conditionals$68_6 = -1;
			if((0 == c9))
				lengthCV$conditionals$68_6 = 2;
			if((1 == c9))
				lengthCV$conditionals$68_6 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_6);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample75) {
			int lengthCV$conditionals$73_6 = -1;
			if((0 == c11))
				lengthCV$conditionals$73_6 = 2;
			if((1 == c11))
				lengthCV$conditionals$73_6 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_6);
		}
		if(!fixedFlag$sample636) {
			int lengthCV$var601$634_9 = -1;
			if((0 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			if((1 == c5)) {
				if((0 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				if((1 == c9)) {
					if((0 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					if((1 == c1)) {
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_9);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample47)
				inferSample47();
			if(!fixedFlag$sample52)
				inferSample52();
			if(!fixedFlag$sample55)
				inferSample55();
			if(!fixedFlag$sample57)
				inferSample57();
			if(!fixedFlag$sample62)
				inferSample62();
			if(!fixedFlag$sample67)
				inferSample67();
			if(!fixedFlag$sample72)
				inferSample72();
		} else {
			if(!fixedFlag$sample72)
				inferSample72();
			if(!fixedFlag$sample67)
				inferSample67();
			if(!fixedFlag$sample62)
				inferSample62();
			if(!fixedFlag$sample57)
				inferSample57();
			if(!fixedFlag$sample55)
				inferSample55();
			if(!fixedFlag$sample52)
				inferSample52();
			if(!fixedFlag$sample47)
				inferSample47();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample47)
			drawValueSample47();
		if(!constrainedFlag$sample52)
			drawValueSample52();
		if(!constrainedFlag$sample55)
			drawValueSample55();
		if(!constrainedFlag$sample57)
			drawValueSample57();
		if(!fixedFlag$sample60)
			drawValueSample60();
		if(!constrainedFlag$sample62)
			drawValueSample62();
		if(!fixedFlag$sample65)
			drawValueSample65();
		if(!constrainedFlag$sample67)
			drawValueSample67();
		if(!fixedFlag$sample70)
			drawValueSample70();
		if(!constrainedFlag$sample72)
			drawValueSample72();
		if(!fixedFlag$sample75)
			drawValueSample75();
		if(!fixedFlag$sample636)
			drawValueSample636();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$c1 = Double.NaN;
		if(!fixedProbFlag$sample50)
			logProbability$c2 = Double.NaN;
		if(!fixedProbFlag$sample52)
			logProbability$c3 = Double.NaN;
		if(!fixedProbFlag$sample55)
			logProbability$c4 = Double.NaN;
		if(!fixedProbFlag$sample57)
			logProbability$c5 = Double.NaN;
		if(!fixedProbFlag$sample60)
			logProbability$c6 = Double.NaN;
		if(!fixedProbFlag$sample62)
			logProbability$c7 = Double.NaN;
		if(!fixedProbFlag$sample65)
			logProbability$c8 = Double.NaN;
		if(!fixedProbFlag$sample67)
			logProbability$c9 = Double.NaN;
		if(!fixedProbFlag$sample70)
			logProbability$c10 = Double.NaN;
		if(!fixedProbFlag$sample72)
			logProbability$c11 = Double.NaN;
		if(!fixedProbFlag$sample75)
			logProbability$c12 = Double.NaN;
		if(!fixedProbFlag$sample636)
			logProbability$terminalVariable = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		priors[0] = 0.01;
		priors[1] = 0.99;
		double[] var15 = conditionals[0];
		var15[0] = 1.0;
		var15[1] = 0.0;
		double[] var30 = conditionals[1];
		var30[0] = 0.0;
		var30[1] = 1.0;
		double[][][][] var77 = a[0];
		double[][][] var79 = var77[0];
		double[][] var81 = var79[0];
		double[] var83 = var81[0];
		var83[0] = 0.3333333333333334;
		var83[1] = 0.3333333333333334;
		var83[2] = 0.0;
		var83[3] = 0.3333333333333334;
		var83[4] = 0.0;
		double[] var110 = var81[1];
		var110[0] = 0.3333333333333334;
		var110[1] = 0.3333333333333334;
		var110[2] = 0.0;
		var110[3] = 0.3333333333333334;
		var110[4] = 0.0;
		double[][] var140 = var79[1];
		double[] var142 = var140[0];
		var142[0] = 0.3333333333333334;
		var142[1] = 0.3333333333333334;
		var142[2] = 0.0;
		var142[3] = 0.3333333333333334;
		var142[4] = 0.0;
		double[] var169 = var140[1];
		var169[0] = 0.5;
		var169[1] = 0.5;
		var169[2] = 0.0;
		var169[3] = 0.0;
		var169[4] = 0.0;
		double[][][] var203 = var77[1];
		double[][] var205 = var203[0];
		double[] var207 = var205[0];
		var207[0] = 0.0;
		var207[1] = 0.5;
		var207[2] = 0.0;
		var207[3] = 0.5;
		var207[4] = 0.0;
		double[] var235 = var205[1];
		var235[0] = 0.0;
		var235[1] = 0.5;
		var235[2] = 0.0;
		var235[3] = 0.5;
		var235[4] = 0.0;
		double[][] var266 = var203[1];
		double[] var268 = var266[0];
		var268[0] = 0.0;
		var268[1] = 0.5;
		var268[2] = 0.0;
		var268[3] = 0.5;
		var268[4] = 0.0;
		double[] var296 = var266[1];
		var296[0] = 0.0;
		var296[1] = 1.0;
		var296[2] = 0.0;
		var296[3] = 0.0;
		var296[4] = 0.0;
		double[][][][] var335 = a[1];
		double[][][] var337 = var335[0];
		double[][] var339 = var337[0];
		double[] var341 = var339[0];
		var341[0] = 0.3333333333333334;
		var341[1] = 0.3333333333333334;
		var341[2] = 0.0;
		var341[3] = 0.3333333333333334;
		var341[4] = 0.0;
		double[] var368 = var339[1];
		var368[0] = 0.5;
		var368[1] = 0.5;
		var368[2] = 0.0;
		var368[3] = 0.0;
		var368[4] = 0.0;
		double[][] var399 = var337[1];
		double[] var401 = var399[0];
		var401[0] = 0.3333333333333334;
		var401[1] = 0.3333333333333334;
		var401[2] = 0.0;
		var401[3] = 0.3333333333333334;
		var401[4] = 0.0;
		double[] var428 = var399[1];
		var428[0] = 0.5;
		var428[1] = 0.5;
		var428[2] = 0.0;
		var428[3] = 0.0;
		var428[4] = 0.0;
		double[][][] var462 = var335[1];
		double[][] var464 = var462[0];
		double[] var466 = var464[0];
		var466[0] = 0.0;
		var466[1] = 0.0;
		var466[2] = 0.0;
		var466[3] = 1.0;
		var466[4] = 0.0;
		double[] var496 = var464[1];
		var496[0] = 0.0;
		var496[1] = 0.0;
		var496[2] = 0.0;
		var496[3] = 1.0;
		var496[4] = 0.0;
		double[][] var529 = var462[1];
		double[] var531 = var529[0];
		var531[0] = 0.0;
		var531[1] = 0.0;
		var531[2] = 0.0;
		var531[3] = 1.0;
		var531[4] = 0.0;
		double[] var561 = var529[1];
		var561[0] = 0.0;
		var561[1] = 0.0;
		var561[2] = 0.0;
		var561[3] = 0.0;
		var561[4] = 1.0;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample60)
			logProbabilityValue$sample60();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(fixedFlag$sample65)
			logProbabilityValue$sample65();
		if(fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(fixedFlag$sample70)
			logProbabilityValue$sample70();
		if(fixedFlag$sample72)
			logProbabilityValue$sample72();
		if(fixedFlag$sample75)
			logProbabilityValue$sample75();
		if(fixedFlag$sample636)
			logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void propagateObservedValues() {
		c2 = evidence;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model TerminalVariables(int evidence) {\n"
		     + "    double[] priors = {.01, 0.99};\n"
		     + "    double[][] conditionals = {{1, 0}, {0, 1}};\n"
		     + "\n"
		     + "    int c1 = categorical(priors).sample();\n"
		     + "    int c2 = categorical(conditionals[c1]).sample();\n"
		     + "    \n"
		     + "    int c3 = categorical(priors).sample();\n"
		     + "    int c4 = categorical(conditionals[c3]).sample();\n"
		     + "    \n"
		     + "    int c5 = categorical(priors).sample();\n"
		     + "    int c6 = categorical(conditionals[c5]).sample();\n"
		     + "\n"
		     + "    int c7 = categorical(priors).sample();\n"
		     + "    int c8 = categorical(conditionals[c7]).sample();\n"
		     + "\n"
		     + "    int c9 = categorical(priors).sample();\n"
		     + "    int c10 = categorical(conditionals[c9]).sample();\n"
		     + "    \n"
		     + "    int c11 = categorical(priors).sample();\n"
		     + "    int c12 = categorical(conditionals[c11]).sample();\n"
		     + "\n"
		     + "    double[][][][][] a = {\n"
		     + "       {\n"
		     + "        {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}},\n"
		     + "         {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "        {{{0, 0.5, 0, 0.5, 0}, {0, 0.5, 0, 0.5, 0}},\n"
		     + "         {{0, 0.5, 0, 0.5, 0}, {0, 1, 0, 0, 0}}}\n"
		     + "       },\n"
		     + "       {\n"
		     + "         {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}},\n"
		     + "          {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "         {{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}},\n"
		     + "          {{0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}}\n"
		     + "        }\n"
		     + "    };\n"
		     + "    int terminalVariable = categorical(a[c5][c9][c1][c4]).sample();\n"
		     + "    \n"
		     + "    // assert\n"
		     + "    c2.observe(evidence);\n"
		     + "}";
	}
}