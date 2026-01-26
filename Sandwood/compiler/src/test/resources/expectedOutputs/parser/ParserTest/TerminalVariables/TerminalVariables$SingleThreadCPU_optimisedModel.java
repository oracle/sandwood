package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements TerminalVariables$CoreInterface {
	
	// Declare the variables for the model.
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

	// Getter for a.
	@Override
	public final double[][][][][] get$a() {
		return a;
	}

	// Getter for c1.
	@Override
	public final int get$c1() {
		return c1;
	}

	// Setter for c1.
	@Override
	public final void set$c1(int cv$value) {
		// Set flags for all the side effects of c1 including if probabilities need to be
		// updated.
		c1 = cv$value;
		
		// Unset the fixed probability flag for sample 47 as it depends on c1.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 50 as it depends on c1.
		fixedProbFlag$sample50 = false;
		
		// Unset the fixed probability flag for sample 636 as it depends on c1.
		fixedProbFlag$sample636 = false;
	}

	// Getter for c10.
	@Override
	public final int get$c10() {
		return c10;
	}

	// Setter for c10.
	@Override
	public final void set$c10(int cv$value) {
		// Set flags for all the side effects of c10 including if probabilities need to be
		// updated.
		c10 = cv$value;
		
		// Unset the fixed probability flag for sample 70 as it depends on c10.
		fixedProbFlag$sample70 = false;
	}

	// Getter for c11.
	@Override
	public final int get$c11() {
		return c11;
	}

	// Setter for c11.
	@Override
	public final void set$c11(int cv$value) {
		// Set flags for all the side effects of c11 including if probabilities need to be
		// updated.
		c11 = cv$value;
		
		// Unset the fixed probability flag for sample 72 as it depends on c11.
		fixedProbFlag$sample72 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on c11.
		fixedProbFlag$sample75 = false;
	}

	// Getter for c12.
	@Override
	public final int get$c12() {
		return c12;
	}

	// Setter for c12.
	@Override
	public final void set$c12(int cv$value) {
		// Set flags for all the side effects of c12 including if probabilities need to be
		// updated.
		c12 = cv$value;
		
		// Unset the fixed probability flag for sample 75 as it depends on c12.
		fixedProbFlag$sample75 = false;
	}

	// Getter for c2.
	@Override
	public final int get$c2() {
		return c2;
	}

	// Getter for c3.
	@Override
	public final int get$c3() {
		return c3;
	}

	// Setter for c3.
	@Override
	public final void set$c3(int cv$value) {
		// Set flags for all the side effects of c3 including if probabilities need to be
		// updated.
		c3 = cv$value;
		
		// Unset the fixed probability flag for sample 52 as it depends on c3.
		fixedProbFlag$sample52 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on c3.
		fixedProbFlag$sample55 = false;
	}

	// Getter for c4.
	@Override
	public final int get$c4() {
		return c4;
	}

	// Setter for c4.
	@Override
	public final void set$c4(int cv$value) {
		// Set flags for all the side effects of c4 including if probabilities need to be
		// updated.
		c4 = cv$value;
		
		// Unset the fixed probability flag for sample 55 as it depends on c4.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 636 as it depends on c4.
		fixedProbFlag$sample636 = false;
	}

	// Getter for c5.
	@Override
	public final int get$c5() {
		return c5;
	}

	// Setter for c5.
	@Override
	public final void set$c5(int cv$value) {
		// Set flags for all the side effects of c5 including if probabilities need to be
		// updated.
		c5 = cv$value;
		
		// Unset the fixed probability flag for sample 57 as it depends on c5.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 60 as it depends on c5.
		fixedProbFlag$sample60 = false;
		
		// Unset the fixed probability flag for sample 636 as it depends on c5.
		fixedProbFlag$sample636 = false;
	}

	// Getter for c6.
	@Override
	public final int get$c6() {
		return c6;
	}

	// Setter for c6.
	@Override
	public final void set$c6(int cv$value) {
		// Set flags for all the side effects of c6 including if probabilities need to be
		// updated.
		c6 = cv$value;
		
		// Unset the fixed probability flag for sample 60 as it depends on c6.
		fixedProbFlag$sample60 = false;
	}

	// Getter for c7.
	@Override
	public final int get$c7() {
		return c7;
	}

	// Setter for c7.
	@Override
	public final void set$c7(int cv$value) {
		// Set flags for all the side effects of c7 including if probabilities need to be
		// updated.
		c7 = cv$value;
		
		// Unset the fixed probability flag for sample 62 as it depends on c7.
		fixedProbFlag$sample62 = false;
		
		// Unset the fixed probability flag for sample 65 as it depends on c7.
		fixedProbFlag$sample65 = false;
	}

	// Getter for c8.
	@Override
	public final int get$c8() {
		return c8;
	}

	// Setter for c8.
	@Override
	public final void set$c8(int cv$value) {
		// Set flags for all the side effects of c8 including if probabilities need to be
		// updated.
		c8 = cv$value;
		
		// Unset the fixed probability flag for sample 65 as it depends on c8.
		fixedProbFlag$sample65 = false;
	}

	// Getter for c9.
	@Override
	public final int get$c9() {
		return c9;
	}

	// Setter for c9.
	@Override
	public final void set$c9(int cv$value) {
		// Set flags for all the side effects of c9 including if probabilities need to be
		// updated.
		c9 = cv$value;
		
		// Unset the fixed probability flag for sample 67 as it depends on c9.
		fixedProbFlag$sample67 = false;
		
		// Unset the fixed probability flag for sample 70 as it depends on c9.
		fixedProbFlag$sample70 = false;
		
		// Unset the fixed probability flag for sample 636 as it depends on c9.
		fixedProbFlag$sample636 = false;
	}

	// Getter for conditionals.
	@Override
	public final double[][] get$conditionals() {
		return conditionals;
	}

	// Getter for evidence.
	@Override
	public final int get$evidence() {
		return evidence;
	}

	// Setter for evidence.
	@Override
	public final void set$evidence(int cv$value) {
		evidence = cv$value;
	}

	// Getter for fixedFlag$sample47.
	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	// Setter for fixedFlag$sample47.
	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
		// need to be updated.
		fixedFlag$sample47 = cv$value;
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		
		// Should the probability of sample 50 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		
		// Should the probability of sample 636 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	// Getter for fixedFlag$sample52.
	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	// Setter for fixedFlag$sample52.
	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
		// need to be updated.
		fixedFlag$sample52 = cv$value;
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		
		// Should the probability of sample 636 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 60 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
		
		// Should the probability of sample 636 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	// Getter for fixedFlag$sample60.
	@Override
	public final boolean get$fixedFlag$sample60() {
		return fixedFlag$sample60;
	}

	// Setter for fixedFlag$sample60.
	@Override
	public final void set$fixedFlag$sample60(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample60 including if probabilities
		// need to be updated.
		fixedFlag$sample60 = cv$value;
		
		// Should the probability of sample 60 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample60" with its value "cv$value".
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
	}

	// Getter for fixedFlag$sample62.
	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	// Setter for fixedFlag$sample62.
	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample62 including if probabilities
		// need to be updated.
		fixedFlag$sample62 = cv$value;
		
		// Should the probability of sample 62 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample62" with its value "cv$value".
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample62" with its value "cv$value".
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	// Getter for fixedFlag$sample636.
	@Override
	public final boolean get$fixedFlag$sample636() {
		return fixedFlag$sample636;
	}

	// Setter for fixedFlag$sample636.
	@Override
	public final void set$fixedFlag$sample636(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample636 including if probabilities
		// need to be updated.
		fixedFlag$sample636 = cv$value;
		
		// Should the probability of sample 636 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample636" with its value "cv$value".
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	// Getter for fixedFlag$sample65.
	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	// Setter for fixedFlag$sample65.
	@Override
	public final void set$fixedFlag$sample65(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample65 including if probabilities
		// need to be updated.
		fixedFlag$sample65 = cv$value;
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample65" with its value "cv$value".
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	// Getter for fixedFlag$sample67.
	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	// Setter for fixedFlag$sample67.
	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample67 including if probabilities
		// need to be updated.
		fixedFlag$sample67 = cv$value;
		
		// Should the probability of sample 67 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
		
		// Should the probability of sample 636 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample636 = (cv$value && fixedProbFlag$sample636);
	}

	// Getter for fixedFlag$sample70.
	@Override
	public final boolean get$fixedFlag$sample70() {
		return fixedFlag$sample70;
	}

	// Setter for fixedFlag$sample70.
	@Override
	public final void set$fixedFlag$sample70(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample70 including if probabilities
		// need to be updated.
		fixedFlag$sample70 = cv$value;
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample70" with its value "cv$value".
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	// Getter for fixedFlag$sample72.
	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	// Setter for fixedFlag$sample72.
	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample72 including if probabilities
		// need to be updated.
		fixedFlag$sample72 = cv$value;
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample72" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample72" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	// Getter for fixedFlag$sample75.
	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	// Setter for fixedFlag$sample75.
	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample75 including if probabilities
		// need to be updated.
		fixedFlag$sample75 = cv$value;
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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

	// Getter for logProbability$c1.
	@Override
	public final double get$logProbability$c1() {
		return logProbability$c1;
	}

	// Getter for logProbability$c10.
	@Override
	public final double get$logProbability$c10() {
		return logProbability$c10;
	}

	// Getter for logProbability$c11.
	@Override
	public final double get$logProbability$c11() {
		return logProbability$c11;
	}

	// Getter for logProbability$c12.
	@Override
	public final double get$logProbability$c12() {
		return logProbability$c12;
	}

	// Getter for logProbability$c2.
	@Override
	public final double get$logProbability$c2() {
		return logProbability$c2;
	}

	// Getter for logProbability$c3.
	@Override
	public final double get$logProbability$c3() {
		return logProbability$c3;
	}

	// Getter for logProbability$c4.
	@Override
	public final double get$logProbability$c4() {
		return logProbability$c4;
	}

	// Getter for logProbability$c5.
	@Override
	public final double get$logProbability$c5() {
		return logProbability$c5;
	}

	// Getter for logProbability$c6.
	@Override
	public final double get$logProbability$c6() {
		return logProbability$c6;
	}

	// Getter for logProbability$c7.
	@Override
	public final double get$logProbability$c7() {
		return logProbability$c7;
	}

	// Getter for logProbability$c8.
	@Override
	public final double get$logProbability$c8() {
		return logProbability$c8;
	}

	// Getter for logProbability$c9.
	@Override
	public final double get$logProbability$c9() {
		return logProbability$c9;
	}

	// Getter for logProbability$terminalVariable.
	@Override
	public final double get$logProbability$terminalVariable() {
		return logProbability$terminalVariable;
	}

	// Getter for priors.
	@Override
	public final double[] get$priors() {
		return priors;
	}

	// Getter for terminalVariable.
	@Override
	public final int get$terminalVariable() {
		return terminalVariable;
	}

	// Setter for terminalVariable.
	@Override
	public final void set$terminalVariable(int cv$value) {
		// Set flags for all the side effects of terminalVariable including if probabilities
		// need to be updated.
		terminalVariable = cv$value;
		
		// Unset the fixed probability flag for sample 636 as it depends on terminalVariable.
		fixedProbFlag$sample636 = false;
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c1) && (c1 < 2)) && (0.0 <= priors[c1])) && (priors[c1] <= 1.0))?Math.log(priors[c1]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c1 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c1);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample50) {
			// Generating probabilities for sample task
			double[] var46 = conditionals[c1];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$48_1 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c1))
				lengthCV$conditionals$48_1 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c1))
				lengthCV$conditionals$48_1 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c2) && (c2 < lengthCV$conditionals$48_1)) && (0 < lengthCV$conditionals$48_1)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c2 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample50 = fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c2);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$c2);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c3) && (c3 < 2)) && (0.0 <= priors[c3])) && (priors[c3] <= 1.0))?Math.log(priors[c3]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c3 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c3);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c3);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			double[] var51 = conditionals[c3];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_3 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c4) && (c4 < lengthCV$conditionals$53_3)) && (0 < lengthCV$conditionals$53_3)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c4 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample52);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c4);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c4);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c5) && (c5 < 2)) && (0.0 <= priors[c5])) && (priors[c5] <= 1.0))?Math.log(priors[c5]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c5 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c5);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c5);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample60) {
			// Generating probabilities for sample task
			double[] var56 = conditionals[c5];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c6) && (c6 < lengthCV$conditionals$58_2)) && (0 < lengthCV$conditionals$58_2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c6 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample60)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample60 = (fixedFlag$sample60 && fixedFlag$sample57);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c6);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample60)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c6);
		}
	}

	// Calculate the probability of the samples represented by sample62 using sampled
	// values.
	private final void logProbabilityValue$sample62() {
		// Determine if we need to calculate the values for sample task 62 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample62) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c7) && (c7 < 2)) && (0.0 <= priors[c7])) && (priors[c7] <= 1.0))?Math.log(priors[c7]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c7 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample62 = fixedFlag$sample62;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c7);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c7);
		}
	}

	// Calculate the probability of the samples represented by sample636 using sampled
	// values.
	private final void logProbabilityValue$sample636() {
		// Determine if we need to calculate the values for sample task 636 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample636) {
			// Generating probabilities for sample task
			double[] var602 = a[c5][c9][c1][c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_5)) && (0 < lengthCV$var601$634_5)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$terminalVariable = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample636)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample636 = ((((fixedFlag$sample636 && fixedFlag$sample47) && fixedFlag$sample55) && fixedFlag$sample57) && fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$terminalVariable);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample636)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$terminalVariable);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			double[] var61 = conditionals[c7];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c8) && (c8 < lengthCV$conditionals$63_2)) && (0 < lengthCV$conditionals$63_2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c8 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample65)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedFlag$sample62);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c8);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample65)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c8);
		}
	}

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample67) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c9) && (c9 < 2)) && (0.0 <= priors[c9])) && (priors[c9] <= 1.0))?Math.log(priors[c9]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c9 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample67 = fixedFlag$sample67;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c9);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c9);
		}
	}

	// Calculate the probability of the samples represented by sample70 using sampled
	// values.
	private final void logProbabilityValue$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample70) {
			// Generating probabilities for sample task
			double[] var66 = conditionals[c9];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c10) && (c10 < lengthCV$conditionals$68_2)) && (0 < lengthCV$conditionals$68_2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c10 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample70)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample70 = (fixedFlag$sample70 && fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c10);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample70)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c10);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= c11) && (c11 < 2)) && (0.0 <= priors[c11])) && (priors[c11] <= 1.0))?Math.log(priors[c11]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c11 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample72)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = fixedFlag$sample72;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample72)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c11);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			double[] var71 = conditionals[c11];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= c12) && (c12 < lengthCV$conditionals$73_2)) && (0 < lengthCV$conditionals$73_2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$c12 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample72);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$c12);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$c12);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void sample47() {
		constrainedFlag$sample47 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c1 = 0;
			
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample47 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double[] var46 = conditionals[0];
			
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 50 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedProbabilities = ((((((0.0 <= c2) && (c2 < 2)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample47 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = a[c5][c9][0][c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_0 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c1 = 1;
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample47 = true;
		
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double[] var46 = conditionals[1];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 50 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		double cv$accumulatedProbabilities = ((((((0.0 <= c2) && (c2 < 2)) && (0.0 <= var46[c2])) && (var46[c2] <= 1.0))?Math.log(var46[c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample47 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = a[c5][c9][1][c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_0 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var45$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var45$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Get a local reference to the scratch space.
			// 
			// Get a local reference to the scratch space.
			// 
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[0] = Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[1] = Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// cv$numStates's comment
		// variable marginalization
		c1 = DistributionSampling.sampleCategorical(RNG$, cv$var45$stateProbabilityGlobal, 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Categorical 49. Inference was performed using variable
	// marginalization.
	private final void sample52() {
		constrainedFlag$sample52 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c3 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample55 || constrainedFlag$sample55)) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample52 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var51 = conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 55 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= c4) && (c4 < 2)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c3 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((fixedFlag$sample55 || constrainedFlag$sample55)) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample52 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var51 = conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 55 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= c4) && (c4 < 2)) && (0.0 <= var51[c4])) && (var51[c4] <= 1.0))?Math.log(var51[c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample52) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var50$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var50$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var50$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var50$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[0] = Math.exp((cv$var50$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[1] = Math.exp((cv$var50$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			c3 = DistributionSampling.sampleCategorical(RNG$, cv$var50$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 52. Inference was performed using variable
	// marginalization.
	private final void sample55() {
		constrainedFlag$sample55 = false;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c3))
			lengthCV$conditionals$53_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c3))
			lengthCV$conditionals$53_1 = 2;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, lengthCV$conditionals$53_1);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			c4 = cv$valuePos;
			
			// Constructing a random variable input for use later.
			double[] var51 = conditionals[c3];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_2 = 2;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$conditionals$53_2) && (0 < lengthCV$conditionals$53_2)) && (0.0 <= var51[cv$valuePos])) && (var51[cv$valuePos] <= 1.0))?Math.log(var51[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample55 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] var602 = a[c5][c9][c1][cv$valuePos];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_1 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_1)) && (0 < lengthCV$var601$634_1)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var53$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample55) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var53$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var53$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialise the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((cv$var53$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$var53$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$var53$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var53$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			c4 = DistributionSampling.sampleCategorical(RNG$, cv$var53$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample57() {
		constrainedFlag$sample57 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c5 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample60) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample57 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var56 = conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 60 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= c6) && (c6 < 2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample57 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = a[0][c9][c1][c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_2 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c5 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample60) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample57 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var56 = conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 60 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= c6) && (c6 < 2)) && (0.0 <= var56[c6])) && (var56[c6] <= 1.0))?Math.log(var56[c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample57 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = a[1][c9][c1][c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample57) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var55$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[0] = Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[1] = Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			c5 = DistributionSampling.sampleCategorical(RNG$, cv$var55$stateProbabilityGlobal, 2);
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample60
	private final void sample60() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c5))
			lengthCV$conditionals$58_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c5))
			lengthCV$conditionals$58_1 = 2;
		c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 62 drawn from Categorical 59. Inference was performed using variable
	// marginalization.
	private final void sample62() {
		constrainedFlag$sample62 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c7 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample65) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample62 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var61 = conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= c8) && (c8 < 2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var60$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c7 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample65) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample62 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var61 = conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 65 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= c8) && (c8 < 2)) && (0.0 <= var61[c8])) && (var61[c8] <= 1.0))?Math.log(var61[c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var60$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample62) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var60$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var60$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var60$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var60$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var60$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var60$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var60$stateProbabilityGlobal[0] = Math.exp((cv$var60$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var60$stateProbabilityGlobal[1] = Math.exp((cv$var60$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var60$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var60$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			c7 = DistributionSampling.sampleCategorical(RNG$, cv$var60$stateProbabilityGlobal, 2);
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample636
	private final void sample636() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_4 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c5)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c5)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_4);
	}

	// Pick a value from the distribution for the unconditioned variable from sample65
	private final void sample65() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c7))
			lengthCV$conditionals$63_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c7))
			lengthCV$conditionals$63_1 = 2;
		c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 67 drawn from Categorical 64. Inference was performed using variable
	// marginalization.
	private final void sample67() {
		constrainedFlag$sample67 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c9 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample70) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample67 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var66 = conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 70 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= c10) && (c10 < 2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample67 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = a[c5][0][c1][c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_3 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var65$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c9 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample70) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample67 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var66 = conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 70 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= c10) && (c10 < 2)) && (0.0 <= var66[c10])) && (var66[c10] <= 1.0))?Math.log(var66[c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample67 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = a[c5][1][c1][c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= terminalVariable) && (terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[terminalVariable])) && (var602[terminalVariable] <= 1.0))?Math.log(var602[terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var65$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample67) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var65$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var65$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var65$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var65$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var65$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var65$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var65$stateProbabilityGlobal[0] = Math.exp((cv$var65$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var65$stateProbabilityGlobal[1] = Math.exp((cv$var65$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var65$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var65$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			c9 = DistributionSampling.sampleCategorical(RNG$, cv$var65$stateProbabilityGlobal, 2);
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample70
	private final void sample70() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c9))
			lengthCV$conditionals$68_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c9))
			lengthCV$conditionals$68_1 = 2;
		c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 72 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void sample72() {
		constrainedFlag$sample72 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			c11 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= priors[0]) && (priors[0] <= 1.0))?Math.log(priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample75) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample72 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var71 = conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= c12) && (c12 < 2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var70$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		c11 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= priors[1]) && (priors[1] <= 1.0))?Math.log(priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample75) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample72 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var71 = conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 75 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= c12) && (c12 < 2)) && (0.0 <= var71[c12])) && (var71[c12] <= 1.0))?Math.log(var71[c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var70$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample72) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialise the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[0] = Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[1] = Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			c11 = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, 2);
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample75
	private final void sample75() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c11))
			lengthCV$conditionals$73_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c11))
			lengthCV$conditionals$73_1 = 2;
		c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_1);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var45$stateProbabilityGlobal
		// 
		// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
		cv$var45$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var50$stateProbabilityGlobal
		// 
		// Allocation of cv$var50$stateProbabilityGlobal for single threaded execution
		cv$var50$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var53$stateProbabilityGlobal for single threaded execution
		// 
		// Test if the input to putTask 44 is larger than the current values.
		cv$var53$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var55$stateProbabilityGlobal
		// 
		// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
		cv$var55$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var60$stateProbabilityGlobal
		// 
		// Allocation of cv$var60$stateProbabilityGlobal for single threaded execution
		cv$var60$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var65$stateProbabilityGlobal
		// 
		// Allocation of cv$var65$stateProbabilityGlobal for single threaded execution
		cv$var65$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var70$stateProbabilityGlobal
		// 
		// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
		cv$var70$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for priors
		priors = new double[2];
		
		// Constructor for conditionals
		conditionals = new double[2][];
		conditionals[0] = new double[2];
		conditionals[1] = new double[2];
		
		// Constructor for a
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
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_2 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c1))
			lengthCV$conditionals$48_2 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c1))
			lengthCV$conditionals$48_2 = 2;
		c2 = DistributionSampling.sampleCategorical(RNG$, conditionals[c1], lengthCV$conditionals$48_2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_4 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_4);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_3 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_3);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_3 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_3);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_3 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_3);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_3 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_3);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_6);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_8 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_8 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_8);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_7 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_7);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_7 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_7);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_7 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_7);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_7 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_7);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_10 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_10);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_3 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == c1))
			lengthCV$conditionals$48_3 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == c1))
			lengthCV$conditionals$48_3 = 2;
		c2 = DistributionSampling.sampleCategorical(RNG$, conditionals[c1], lengthCV$conditionals$48_3);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_5 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_5);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_4 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_4);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_4 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_4);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_4 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_4);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_4 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_4);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_7);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_6 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_6);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_5 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_5);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_5 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_5);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_5 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_5);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_5 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_8);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample47)
			c1 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		if(!fixedFlag$sample52)
			c3 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c3))
				lengthCV$conditionals$53_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c3))
				lengthCV$conditionals$53_7 = 2;
			c4 = DistributionSampling.sampleCategorical(RNG$, conditionals[c3], lengthCV$conditionals$53_7);
		}
		if(!fixedFlag$sample57)
			c5 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5))
				lengthCV$conditionals$58_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5))
				lengthCV$conditionals$58_6 = 2;
			c6 = DistributionSampling.sampleCategorical(RNG$, conditionals[c5], lengthCV$conditionals$58_6);
		}
		if(!fixedFlag$sample62)
			c7 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c7))
				lengthCV$conditionals$63_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c7))
				lengthCV$conditionals$63_6 = 2;
			c8 = DistributionSampling.sampleCategorical(RNG$, conditionals[c7], lengthCV$conditionals$63_6);
		}
		if(!fixedFlag$sample67)
			c9 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c9))
				lengthCV$conditionals$68_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c9))
				lengthCV$conditionals$68_6 = 2;
			c10 = DistributionSampling.sampleCategorical(RNG$, conditionals[c9], lengthCV$conditionals$68_6);
		}
		if(!fixedFlag$sample72)
			c11 = DistributionSampling.sampleCategorical(RNG$, priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c11))
				lengthCV$conditionals$73_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c11))
				lengthCV$conditionals$73_6 = 2;
			c12 = DistributionSampling.sampleCategorical(RNG$, conditionals[c11], lengthCV$conditionals$73_6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_9 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			terminalVariable = DistributionSampling.sampleCategorical(RNG$, a[c5][c9][c1][c4], lengthCV$var601$634_9);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample47)
				sample47();
			if(!fixedFlag$sample52)
				sample52();
			if(!fixedFlag$sample55)
				sample55();
			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample62)
				sample62();
			if(!fixedFlag$sample67)
				sample67();
			if(!fixedFlag$sample72)
				sample72();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample72)
				sample72();
			if(!fixedFlag$sample67)
				sample67();
			if(!fixedFlag$sample62)
				sample62();
			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample55)
				sample55();
			if(!fixedFlag$sample52)
				sample52();
			if(!fixedFlag$sample47)
				sample47();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		if(!fixedFlag$sample60)
			sample60();
		if(!fixedFlag$sample65)
			sample65();
		if(!fixedFlag$sample70)
			sample70();
		if(!fixedFlag$sample75)
			sample75();
		if(!fixedFlag$sample636)
			sample636();
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
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

	// Method for initialising the model into a valid state before commencing inference
	// etc.
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

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
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

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
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

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
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

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		c2 = evidence;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
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