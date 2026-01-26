package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements NoisyOr$CoreInterface {
	
	// Declare the variables for the model.
	private boolean constrainedFlag$sample12 = true;
	private boolean constrainedFlag$sample15 = true;
	private boolean constrainedFlag$sample18 = true;
	private boolean[] constrainedFlag$sample233;
	private boolean[] constrainedFlag$sample248;
	private boolean[] constrainedFlag$sample263;
	private boolean[] constrainedFlag$sample278;
	private boolean[] constrainedFlag$sample293;
	private boolean constrainedFlag$sample3 = true;
	private boolean[] constrainedFlag$sample308;
	private boolean constrainedFlag$sample6 = true;
	private boolean constrainedFlag$sample9 = true;
	private double[] cv$var12$stateProbabilityGlobal;
	private double[] cv$var15$stateProbabilityGlobal;
	private double[] cv$var18$stateProbabilityGlobal;
	private double[] cv$var225$stateProbabilityGlobal;
	private double[] cv$var238$stateProbabilityGlobal;
	private double[] cv$var251$stateProbabilityGlobal;
	private double[] cv$var264$stateProbabilityGlobal;
	private double[] cv$var277$stateProbabilityGlobal;
	private double[] cv$var290$stateProbabilityGlobal;
	private double[] cv$var3$stateProbabilityGlobal;
	private double[] cv$var6$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample233 = false;
	private boolean fixedFlag$sample248 = false;
	private boolean fixedFlag$sample263 = false;
	private boolean fixedFlag$sample278 = false;
	private boolean fixedFlag$sample293 = false;
	private boolean fixedFlag$sample3 = false;
	private boolean fixedFlag$sample308 = false;
	private boolean fixedFlag$sample430 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample233 = false;
	private boolean fixedProbFlag$sample248 = false;
	private boolean fixedProbFlag$sample263 = false;
	private boolean fixedProbFlag$sample278 = false;
	private boolean fixedProbFlag$sample293 = false;
	private boolean fixedProbFlag$sample3 = false;
	private boolean fixedProbFlag$sample308 = false;
	private boolean fixedProbFlag$sample430 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;
	private boolean flag5;
	private boolean flag6;
	private boolean[][] issues$var213;
	private boolean[][] issues$var383;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$flag1;
	private double logProbability$flag2;
	private double logProbability$flag3;
	private double logProbability$flag4;
	private double logProbability$flag5;
	private double logProbability$flag6;
	private double logProbability$issues$var213;
	private double logProbability$issues$var383;
	private double logProbability$n13State;
	private double logProbability$noisyOr;
	private double[] logProbability$sample233;
	private double[] logProbability$sample248;
	private double[] logProbability$sample263;
	private double[] logProbability$sample278;
	private double[] logProbability$sample293;
	private double[] logProbability$sample308;
	private double[][] logProbability$sample430;
	private boolean[] n13State;
	private boolean[] noisyOr;
	private double[][] p;
	private double[][] p13;
	private boolean system$gibbsForward = true;

	public NoisyOr$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample12.
	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	// Setter for fixedFlag$sample12.
	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
		// need to be updated.
		fixedFlag$sample12 = cv$value;
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		
		// Should the probability of sample 278 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample278 = (cv$value && fixedProbFlag$sample278);
	}

	// Getter for fixedFlag$sample15.
	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	// Setter for fixedFlag$sample15.
	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample15 including if probabilities
		// need to be updated.
		fixedFlag$sample15 = cv$value;
		
		// Should the probability of sample 15 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample15" with its value "cv$value".
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		
		// Should the probability of sample 293 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample15" with its value "cv$value".
		fixedProbFlag$sample293 = (cv$value && fixedProbFlag$sample293);
	}

	// Getter for fixedFlag$sample18.
	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	// Setter for fixedFlag$sample18.
	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample18 including if probabilities
		// need to be updated.
		fixedFlag$sample18 = cv$value;
		
		// Should the probability of sample 18 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		
		// Should the probability of sample 308 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample308 = (cv$value && fixedProbFlag$sample308);
	}

	// Getter for fixedFlag$sample233.
	@Override
	public final boolean get$fixedFlag$sample233() {
		return fixedFlag$sample233;
	}

	// Setter for fixedFlag$sample233.
	@Override
	public final void set$fixedFlag$sample233(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample233 including if probabilities
		// need to be updated.
		fixedFlag$sample233 = cv$value;
		
		// Should the probability of sample 233 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample233" with its value "cv$value".
		fixedProbFlag$sample233 = (cv$value && fixedProbFlag$sample233);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample233" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample248.
	@Override
	public final boolean get$fixedFlag$sample248() {
		return fixedFlag$sample248;
	}

	// Setter for fixedFlag$sample248.
	@Override
	public final void set$fixedFlag$sample248(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample248 including if probabilities
		// need to be updated.
		fixedFlag$sample248 = cv$value;
		
		// Should the probability of sample 248 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample248" with its value "cv$value".
		fixedProbFlag$sample248 = (cv$value && fixedProbFlag$sample248);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample248" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample263.
	@Override
	public final boolean get$fixedFlag$sample263() {
		return fixedFlag$sample263;
	}

	// Setter for fixedFlag$sample263.
	@Override
	public final void set$fixedFlag$sample263(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample263 including if probabilities
		// need to be updated.
		fixedFlag$sample263 = cv$value;
		
		// Should the probability of sample 263 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample263" with its value "cv$value".
		fixedProbFlag$sample263 = (cv$value && fixedProbFlag$sample263);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample263" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample278.
	@Override
	public final boolean get$fixedFlag$sample278() {
		return fixedFlag$sample278;
	}

	// Setter for fixedFlag$sample278.
	@Override
	public final void set$fixedFlag$sample278(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample278 including if probabilities
		// need to be updated.
		fixedFlag$sample278 = cv$value;
		
		// Should the probability of sample 278 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample278" with its value "cv$value".
		fixedProbFlag$sample278 = (cv$value && fixedProbFlag$sample278);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample278" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample293.
	@Override
	public final boolean get$fixedFlag$sample293() {
		return fixedFlag$sample293;
	}

	// Setter for fixedFlag$sample293.
	@Override
	public final void set$fixedFlag$sample293(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample293 including if probabilities
		// need to be updated.
		fixedFlag$sample293 = cv$value;
		
		// Should the probability of sample 293 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample293" with its value "cv$value".
		fixedProbFlag$sample293 = (cv$value && fixedProbFlag$sample293);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample293" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample3.
	@Override
	public final boolean get$fixedFlag$sample3() {
		return fixedFlag$sample3;
	}

	// Setter for fixedFlag$sample3.
	@Override
	public final void set$fixedFlag$sample3(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample3 including if probabilities
		// need to be updated.
		fixedFlag$sample3 = cv$value;
		
		// Should the probability of sample 3 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample3" with its value "cv$value".
		fixedProbFlag$sample3 = (cv$value && fixedProbFlag$sample3);
		
		// Should the probability of sample 233 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample3" with its value "cv$value".
		fixedProbFlag$sample233 = (cv$value && fixedProbFlag$sample233);
	}

	// Getter for fixedFlag$sample308.
	@Override
	public final boolean get$fixedFlag$sample308() {
		return fixedFlag$sample308;
	}

	// Setter for fixedFlag$sample308.
	@Override
	public final void set$fixedFlag$sample308(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample308 including if probabilities
		// need to be updated.
		fixedFlag$sample308 = cv$value;
		
		// Should the probability of sample 308 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample308" with its value "cv$value".
		fixedProbFlag$sample308 = (cv$value && fixedProbFlag$sample308);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample308" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample430.
	@Override
	public final boolean get$fixedFlag$sample430() {
		return fixedFlag$sample430;
	}

	// Setter for fixedFlag$sample430.
	@Override
	public final void set$fixedFlag$sample430(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample430 including if probabilities
		// need to be updated.
		fixedFlag$sample430 = cv$value;
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample430" with its value "cv$value".
		fixedProbFlag$sample430 = (cv$value && fixedProbFlag$sample430);
	}

	// Getter for fixedFlag$sample6.
	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	// Setter for fixedFlag$sample6.
	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample6 including if probabilities
		// need to be updated.
		fixedFlag$sample6 = cv$value;
		
		// Should the probability of sample 6 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample6" with its value "cv$value".
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		
		// Should the probability of sample 248 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample6" with its value "cv$value".
		fixedProbFlag$sample248 = (cv$value && fixedProbFlag$sample248);
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		
		// Should the probability of sample 263 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample263 = (cv$value && fixedProbFlag$sample263);
	}

	// Getter for flag1.
	@Override
	public final boolean get$flag1() {
		return flag1;
	}

	// Setter for flag1.
	@Override
	public final void set$flag1(boolean cv$value) {
		// Set flags for all the side effects of flag1 including if probabilities need to
		// be updated.
		flag1 = cv$value;
		
		// Unset the fixed probability flag for sample 3 as it depends on flag1.
		fixedProbFlag$sample3 = false;
		
		// Unset the fixed probability flag for sample 233 as it depends on flag1.
		fixedProbFlag$sample233 = false;
	}

	// Getter for flag2.
	@Override
	public final boolean get$flag2() {
		return flag2;
	}

	// Setter for flag2.
	@Override
	public final void set$flag2(boolean cv$value) {
		// Set flags for all the side effects of flag2 including if probabilities need to
		// be updated.
		flag2 = cv$value;
		
		// Unset the fixed probability flag for sample 6 as it depends on flag2.
		fixedProbFlag$sample6 = false;
		
		// Unset the fixed probability flag for sample 248 as it depends on flag2.
		fixedProbFlag$sample248 = false;
	}

	// Getter for flag3.
	@Override
	public final boolean get$flag3() {
		return flag3;
	}

	// Setter for flag3.
	@Override
	public final void set$flag3(boolean cv$value) {
		// Set flags for all the side effects of flag3 including if probabilities need to
		// be updated.
		flag3 = cv$value;
		
		// Unset the fixed probability flag for sample 9 as it depends on flag3.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 263 as it depends on flag3.
		fixedProbFlag$sample263 = false;
	}

	// Getter for flag4.
	@Override
	public final boolean get$flag4() {
		return flag4;
	}

	// Setter for flag4.
	@Override
	public final void set$flag4(boolean cv$value) {
		// Set flags for all the side effects of flag4 including if probabilities need to
		// be updated.
		flag4 = cv$value;
		
		// Unset the fixed probability flag for sample 12 as it depends on flag4.
		fixedProbFlag$sample12 = false;
		
		// Unset the fixed probability flag for sample 278 as it depends on flag4.
		fixedProbFlag$sample278 = false;
	}

	// Getter for flag5.
	@Override
	public final boolean get$flag5() {
		return flag5;
	}

	// Setter for flag5.
	@Override
	public final void set$flag5(boolean cv$value) {
		// Set flags for all the side effects of flag5 including if probabilities need to
		// be updated.
		flag5 = cv$value;
		
		// Unset the fixed probability flag for sample 15 as it depends on flag5.
		fixedProbFlag$sample15 = false;
		
		// Unset the fixed probability flag for sample 293 as it depends on flag5.
		fixedProbFlag$sample293 = false;
	}

	// Getter for flag6.
	@Override
	public final boolean get$flag6() {
		return flag6;
	}

	// Setter for flag6.
	@Override
	public final void set$flag6(boolean cv$value) {
		// Set flags for all the side effects of flag6 including if probabilities need to
		// be updated.
		flag6 = cv$value;
		
		// Unset the fixed probability flag for sample 18 as it depends on flag6.
		fixedProbFlag$sample18 = false;
		
		// Unset the fixed probability flag for sample 308 as it depends on flag6.
		fixedProbFlag$sample308 = false;
	}

	// Getter for issues$var213.
	@Override
	public final boolean[][] get$issues$var213() {
		return issues$var213;
	}

	// Setter for issues$var213.
	@Override
	public final void set$issues$var213(boolean[][] cv$value) {
		// Set issues$var213
		issues$var213 = cv$value;
	}

	// Getter for issues$var383.
	@Override
	public final boolean[][] get$issues$var383() {
		return issues$var383;
	}

	// Setter for issues$var383.
	@Override
	public final void set$issues$var383(boolean[][] cv$value) {
		// Set issues$var383
		issues$var383 = cv$value;
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

	// Getter for logProbability$flag1.
	@Override
	public final double get$logProbability$flag1() {
		return logProbability$flag1;
	}

	// Getter for logProbability$flag2.
	@Override
	public final double get$logProbability$flag2() {
		return logProbability$flag2;
	}

	// Getter for logProbability$flag3.
	@Override
	public final double get$logProbability$flag3() {
		return logProbability$flag3;
	}

	// Getter for logProbability$flag4.
	@Override
	public final double get$logProbability$flag4() {
		return logProbability$flag4;
	}

	// Getter for logProbability$flag5.
	@Override
	public final double get$logProbability$flag5() {
		return logProbability$flag5;
	}

	// Getter for logProbability$flag6.
	@Override
	public final double get$logProbability$flag6() {
		return logProbability$flag6;
	}

	// Getter for logProbability$n13State.
	@Override
	public final double get$logProbability$n13State() {
		return logProbability$n13State;
	}

	// Getter for logProbability$noisyOr.
	@Override
	public final double get$logProbability$noisyOr() {
		return logProbability$noisyOr;
	}

	// Getter for n13State.
	@Override
	public final boolean[] get$n13State() {
		return n13State;
	}

	// Getter for noisyOr.
	@Override
	public final boolean[] get$noisyOr() {
		return noisyOr;
	}

	// Getter for p.
	@Override
	public final double[][] get$p() {
		return p;
	}

	// Getter for p13.
	@Override
	public final double[][] get$p13() {
		return p13;
	}

	// Getter for prior1.
	@Override
	public final double get$prior1() {
		return 0.01;
	}

	// Getter for prior2.
	@Override
	public final double get$prior2() {
		return 0.01;
	}

	// Getter for prior3.
	@Override
	public final double get$prior3() {
		return 0.01;
	}

	// Getter for prior4.
	@Override
	public final double get$prior4() {
		return 0.01;
	}

	// Getter for prior5.
	@Override
	public final double get$prior5() {
		return 0.01;
	}

	// Getter for prior6.
	@Override
	public final double get$prior6() {
		return 0.01;
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
			double cv$distributionAccumulator = Math.log((flag4?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag4 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample12)
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
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag4);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag4);
		}
	}

	// Calculate the probability of the samples represented by sample15 using sampled
	// values.
	private final void logProbabilityValue$sample15() {
		// Determine if we need to calculate the values for sample task 15 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample15) {
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
			double cv$distributionAccumulator = Math.log((flag5?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag5 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample15)
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
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag5);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag5);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
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
			double cv$distributionAccumulator = Math.log((flag6?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag6 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample18)
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
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag6);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag6);
		}
	}

	// Calculate the probability of the samples represented by sample233 using sampled
	// values.
	private final void logProbabilityValue$sample233() {
		// Determine if we need to calculate the values for sample task 233 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample233) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((issues$var213[i$var211][0]?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample233[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample233)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample233 = (fixedFlag$sample233 && fixedFlag$sample3);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample233[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample233)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample248 using sampled
	// values.
	private final void logProbabilityValue$sample248() {
		// Determine if we need to calculate the values for sample task 248 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample248) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((issues$var213[i$var211][1]?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample248[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample248)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample248 = (fixedFlag$sample248 && fixedFlag$sample6);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample248[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample248)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample263 using sampled
	// values.
	private final void logProbabilityValue$sample263() {
		// Determine if we need to calculate the values for sample task 263 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample263) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((issues$var213[i$var211][2]?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample263[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample263)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample263 = (fixedFlag$sample263 && fixedFlag$sample9);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample263[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample263)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample278 using sampled
	// values.
	private final void logProbabilityValue$sample278() {
		// Determine if we need to calculate the values for sample task 278 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample278) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((issues$var213[i$var211][3]?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample278[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample278)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample278 = (fixedFlag$sample278 && fixedFlag$sample12);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample278[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample278)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample293 using sampled
	// values.
	private final void logProbabilityValue$sample293() {
		// Determine if we need to calculate the values for sample task 293 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample293) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((issues$var213[i$var211][4]?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample293[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample293 = (fixedFlag$sample293 && fixedFlag$sample15);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample293[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample293)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample3 using sampled values.
	private final void logProbabilityValue$sample3() {
		// Determine if we need to calculate the values for sample task 3 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample3) {
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
			double cv$distributionAccumulator = Math.log((flag1?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag1 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample3)
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
			fixedProbFlag$sample3 = fixedFlag$sample3;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample3)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag1);
		}
	}

	// Calculate the probability of the samples represented by sample308 using sampled
	// values.
	private final void logProbabilityValue$sample308() {
		// Determine if we need to calculate the values for sample task 308 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample308) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((issues$var213[i$var211][5]?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample308[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample308)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample308 = (fixedFlag$sample308 && fixedFlag$sample18);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = logProbability$sample308[i$var211];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var213 = (logProbability$issues$var213 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample308)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample430 using sampled
	// values.
	private final void logProbabilityValue$sample430() {
		// Determine if we need to calculate the values for sample task 430 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample430) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "0".
					var402 = p13[j][0];
				else
					var402 = 0.0;
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "i$var381" with its value "0".
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((issues$var383[0][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Substituted "i$var381" with its value "0".
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample430[0][j] = cv$weightedProbability;
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$n13State = (logProbability$n13State + cv$weightedProbability);
			}
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "i$var381" with its value "1".
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((issues$var383[1][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Substituted "i$var381" with its value "1".
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample430[1][j] = cv$weightedProbability;
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$n13State = (logProbability$n13State + cv$weightedProbability);
			}
			
			// Update the variable probability
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample430 = ((((((fixedFlag$sample430 && fixedFlag$sample233) && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				// Substituted "i$var381" with its value "0".
				double cv$sampleValue = logProbability$sample430[0][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$n13State = (logProbability$n13State + cv$sampleValue);
			}
			for(int j = 0; j < 5; j += 1) {
				// Substituted "i$var381" with its value "1".
				double cv$sampleValue = logProbability$sample430[1][j];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
				// Update the variable probability
				logProbability$n13State = (logProbability$n13State + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$issues$var383 = (logProbability$issues$var383 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample430)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Determine if we need to calculate the values for sample task 6 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample6) {
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
			double cv$distributionAccumulator = Math.log((flag2?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag2 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample6)
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
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag2);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag2);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
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
			double cv$distributionAccumulator = Math.log((flag3?0.01:0.99));
			
			// Store the sample task probability
			logProbability$flag3 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample9)
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
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flag3);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$flag3);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from Bernoulli 11. Inference was performed using variable
	// marginalization.
	private final void sample12() {
		constrainedFlag$sample12 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag4 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point276.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample278 || constrainedFlag$sample278[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample12 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 278 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][3]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var12$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag4 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point276.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample278 || constrainedFlag$sample278[i$var211])) {
				double traceTempVariable$var262$2_1 = p[3][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample12 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 278 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[i$var211][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var12$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample12) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var12$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var12$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var12$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var12$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var12$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var12$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var12$stateProbabilityGlobal[0] = Math.exp((cv$var12$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var12$stateProbabilityGlobal[1] = Math.exp((cv$var12$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var12$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var12$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag4 = (DistributionSampling.sampleCategorical(RNG$, cv$var12$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 15 drawn from Bernoulli 14. Inference was performed using variable
	// marginalization.
	private final void sample15() {
		constrainedFlag$sample15 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag5 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point291.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample293 || constrainedFlag$sample293[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample15 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 293 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][4]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var15$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag5 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point291.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample293 || constrainedFlag$sample293[i$var211])) {
				double traceTempVariable$var275$2_1 = p[4][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample15 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 293 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[i$var211][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var15$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample15) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var15$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var15$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var15$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var15$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var15$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var15$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var15$stateProbabilityGlobal[0] = Math.exp((cv$var15$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var15$stateProbabilityGlobal[1] = Math.exp((cv$var15$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var15$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag5 = (DistributionSampling.sampleCategorical(RNG$, cv$var15$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Bernoulli 17. Inference was performed using variable
	// marginalization.
	private final void sample18() {
		constrainedFlag$sample18 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag6 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point306.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample308 || constrainedFlag$sample308[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample18 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 308 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][5]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var18$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag6 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point306.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample308 || constrainedFlag$sample308[i$var211])) {
				double traceTempVariable$var288$2_1 = p[5][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample18 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 308 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[i$var211][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var18$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample18) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var18$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var18$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var18$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var18$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var18$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var18$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var18$stateProbabilityGlobal[0] = Math.exp((cv$var18$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var18$stateProbabilityGlobal[1] = Math.exp((cv$var18$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var18$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var18$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag6 = (DistributionSampling.sampleCategorical(RNG$, cv$var18$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 233 drawn from Bernoulli 224. Inference was performed using variable
	// marginalization.
	private final void sample233(int i$var211) {
		constrainedFlag$sample233[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][0] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$0 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$0 = (reduceVar$var300$0 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$0;
			double var223;
			if(flag1)
				var223 = p[0][i$var211];
			else
				var223 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((1.0 - var223)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample233[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample233[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var225$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][0] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$0 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$0 = (reduceVar$var300$0 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$0;
		double var223;
		if(flag1)
			var223 = p[0][i$var211];
		else
			var223 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log(var223):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample233[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample233[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var225$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample233[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var225$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var225$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var225$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var225$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var225$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var225$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var225$stateProbabilityGlobal[0] = Math.exp((cv$var225$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var225$stateProbabilityGlobal[1] = Math.exp((cv$var225$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var225$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var225$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][0] = (DistributionSampling.sampleCategorical(RNG$, cv$var225$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$1 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$1 = (reduceVar$var300$1 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$1;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 248 drawn from Bernoulli 237. Inference was performed using variable
	// marginalization.
	private final void sample248(int i$var211) {
		constrainedFlag$sample248[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][1] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$2 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$2 = (reduceVar$var300$2 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$2;
			double var236;
			if(flag2)
				var236 = p[1][i$var211];
			else
				var236 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((1.0 - var236)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample248[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample248[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var238$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][1] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$2 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$2 = (reduceVar$var300$2 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$2;
		double var236;
		if(flag2)
			var236 = p[1][i$var211];
		else
			var236 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log(var236):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample248[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample248[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var238$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample248[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var238$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var238$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var238$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var238$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var238$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var238$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var238$stateProbabilityGlobal[0] = Math.exp((cv$var238$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var238$stateProbabilityGlobal[1] = Math.exp((cv$var238$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var238$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var238$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][1] = (DistributionSampling.sampleCategorical(RNG$, cv$var238$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$3 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$3 = (reduceVar$var300$3 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$3;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 263 drawn from Bernoulli 250. Inference was performed using variable
	// marginalization.
	private final void sample263(int i$var211) {
		constrainedFlag$sample263[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][2] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$4 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$4 = (reduceVar$var300$4 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$4;
			double var249;
			if(flag3)
				var249 = p[2][i$var211];
			else
				var249 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((1.0 - var249)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample263[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample263[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var251$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][2] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$4 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$4 = (reduceVar$var300$4 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$4;
		double var249;
		if(flag3)
			var249 = p[2][i$var211];
		else
			var249 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log(var249):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample263[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample263[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var251$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample263[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var251$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var251$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var251$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var251$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var251$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var251$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var251$stateProbabilityGlobal[0] = Math.exp((cv$var251$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var251$stateProbabilityGlobal[1] = Math.exp((cv$var251$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var251$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var251$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][2] = (DistributionSampling.sampleCategorical(RNG$, cv$var251$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$5 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$5 = (reduceVar$var300$5 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$5;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 278 drawn from Bernoulli 263. Inference was performed using variable
	// marginalization.
	private final void sample278(int i$var211) {
		constrainedFlag$sample278[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][3] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$6 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$6 = (reduceVar$var300$6 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$6;
			double var262;
			if(flag4)
				var262 = p[3][i$var211];
			else
				var262 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((1.0 - var262)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample278[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample278[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var264$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][3] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$6 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$6 = (reduceVar$var300$6 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$6;
		double var262;
		if(flag4)
			var262 = p[3][i$var211];
		else
			var262 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log(var262):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample278[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample278[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var264$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample278[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var264$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var264$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var264$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var264$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var264$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var264$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var264$stateProbabilityGlobal[0] = Math.exp((cv$var264$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var264$stateProbabilityGlobal[1] = Math.exp((cv$var264$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var264$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var264$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][3] = (DistributionSampling.sampleCategorical(RNG$, cv$var264$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$7 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$7 = (reduceVar$var300$7 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$7;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 293 drawn from Bernoulli 276. Inference was performed using variable
	// marginalization.
	private final void sample293(int i$var211) {
		constrainedFlag$sample293[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][4] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$8 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$8 = (reduceVar$var300$8 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$8;
			double var275;
			if(flag5)
				var275 = p[4][i$var211];
			else
				var275 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((1.0 - var275)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample293[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample293[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var277$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][4] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$8 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$8 = (reduceVar$var300$8 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$8;
		double var275;
		if(flag5)
			var275 = p[4][i$var211];
		else
			var275 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log(var275):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample293[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample293[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var277$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample293[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var277$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var277$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var277$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var277$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var277$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var277$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var277$stateProbabilityGlobal[0] = Math.exp((cv$var277$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var277$stateProbabilityGlobal[1] = Math.exp((cv$var277$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var277$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var277$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][4] = (DistributionSampling.sampleCategorical(RNG$, cv$var277$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$9 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$9 = (reduceVar$var300$9 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$9;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 3 drawn from Bernoulli 2. Inference was performed using variable
	// marginalization.
	private final void sample3() {
		constrainedFlag$sample3 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag1 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point231.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample233 || constrainedFlag$sample233[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample3 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 233 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][0]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var3$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag1 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point231.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample233 || constrainedFlag$sample233[i$var211])) {
				double traceTempVariable$var223$2_1 = p[0][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample3 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 233 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[i$var211][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var3$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample3) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var3$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var3$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var3$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var3$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var3$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var3$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var3$stateProbabilityGlobal[0] = Math.exp((cv$var3$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var3$stateProbabilityGlobal[1] = Math.exp((cv$var3$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var3$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var3$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag1 = (DistributionSampling.sampleCategorical(RNG$, cv$var3$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 308 drawn from Bernoulli 289. Inference was performed using variable
	// marginalization.
	private final void sample308(int i$var211) {
		constrainedFlag$sample308[i$var211] = false;
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			issues$var213[i$var211][5] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$10 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$10 = (reduceVar$var300$10 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$10;
			double var288;
			if(flag6)
				var288 = p[5][i$var211];
			else
				var288 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((1.0 - var288)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(noisyOr[i$var211]) {
					{
						// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample308[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample308[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var290$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		issues$var213[i$var211][5] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$10 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$10 = (reduceVar$var300$10 || issues$var213[i$var211][cv$reduction313Index]);
		noisyOr[i$var211] = reduceVar$var300$10;
		double var288;
		if(flag6)
			var288 = p[5][i$var211];
		else
			var288 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log(var288):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(noisyOr[i$var211]) {
				{
					// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample308[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample308[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var290$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample308[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var290$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var290$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var290$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var290$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var290$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var290$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var290$stateProbabilityGlobal[0] = Math.exp((cv$var290$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var290$stateProbabilityGlobal[1] = Math.exp((cv$var290$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var290$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var290$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			issues$var213[i$var211][5] = (DistributionSampling.sampleCategorical(RNG$, cv$var290$stateProbabilityGlobal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$11 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$11 = (reduceVar$var300$11 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$11;
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample430
	private final void sample430(int i$var381, int j) {
		double var402;
		if(noisyOr[j])
			var402 = p13[j][i$var381];
		else
			var402 = 0.0;
		issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		
		// Guards to ensure that n13State is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$0 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// y$var412's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var414$0 = (reduceVar$var414$0 || issues$var383[i$var381][cv$reduction435Index]);
		n13State[i$var381] = reduceVar$var414$0;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Bernoulli 5. Inference was performed using variable
	// marginalization.
	private final void sample6() {
		constrainedFlag$sample6 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag2 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point246.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample248 || constrainedFlag$sample248[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample6 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 248 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][1]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var6$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag2 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point246.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample248 || constrainedFlag$sample248[i$var211])) {
				double traceTempVariable$var236$2_1 = p[1][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample6 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 248 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[i$var211][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var6$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample6) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var6$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var6$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var6$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var6$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var6$stateProbabilityGlobal[0] = Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var6$stateProbabilityGlobal[1] = Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var6$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag2 = (DistributionSampling.sampleCategorical(RNG$, cv$var6$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Bernoulli 8. Inference was performed using variable
	// marginalization.
	private final void sample9() {
		constrainedFlag$sample9 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			flag3 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point261.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample263 || constrainedFlag$sample263[i$var211])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample9 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 263 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((issues$var213[i$var211][2]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var9$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		flag3 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point261.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample263 || constrainedFlag$sample263[i$var211])) {
				double traceTempVariable$var249$2_1 = p[2][i$var211];
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample9 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 263 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[i$var211][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var9$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample9) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var9$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var9$stateProbabilityGlobal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$var9$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var9$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var9$stateProbabilityGlobal[0] = 0.5;
				
				// Get a local reference to the scratch space.
				cv$var9$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
				// Get a local reference to the scratch space.
				cv$var9$stateProbabilityGlobal[0] = Math.exp((cv$var9$stateProbabilityGlobal[0] - cv$logSum));
				
				// Get a local reference to the scratch space.
				cv$var9$stateProbabilityGlobal[1] = Math.exp((cv$var9$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var9$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// cv$numStates's comment
			// variable marginalization
			flag3 = (DistributionSampling.sampleCategorical(RNG$, cv$var9$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var3$stateProbabilityGlobal
		// 
		// Allocation of cv$var3$stateProbabilityGlobal for single threaded execution
		cv$var3$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var6$stateProbabilityGlobal
		// 
		// Allocation of cv$var6$stateProbabilityGlobal for single threaded execution
		cv$var6$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var9$stateProbabilityGlobal
		// 
		// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
		cv$var9$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var12$stateProbabilityGlobal
		// 
		// Allocation of cv$var12$stateProbabilityGlobal for single threaded execution
		cv$var12$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var15$stateProbabilityGlobal
		// 
		// Allocation of cv$var15$stateProbabilityGlobal for single threaded execution
		cv$var15$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var18$stateProbabilityGlobal
		// 
		// Allocation of cv$var18$stateProbabilityGlobal for single threaded execution
		cv$var18$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var225$stateProbabilityGlobal
		// 
		// Allocation of cv$var225$stateProbabilityGlobal for single threaded execution
		cv$var225$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var238$stateProbabilityGlobal
		// 
		// Allocation of cv$var238$stateProbabilityGlobal for single threaded execution
		cv$var238$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var251$stateProbabilityGlobal
		// 
		// Allocation of cv$var251$stateProbabilityGlobal for single threaded execution
		cv$var251$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var264$stateProbabilityGlobal
		// 
		// Allocation of cv$var264$stateProbabilityGlobal for single threaded execution
		cv$var264$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var277$stateProbabilityGlobal
		// 
		// Allocation of cv$var277$stateProbabilityGlobal for single threaded execution
		cv$var277$stateProbabilityGlobal = new double[2];
		
		// Constructor for cv$var290$stateProbabilityGlobal
		// 
		// Allocation of cv$var290$stateProbabilityGlobal for single threaded execution
		cv$var290$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for p
		p = new double[6][];
		p[0] = new double[5];
		p[1] = new double[5];
		p[2] = new double[5];
		p[3] = new double[5];
		p[4] = new double[5];
		p[5] = new double[5];
		
		// Constructor for noisyOr
		noisyOr = new boolean[5];
		
		// If issues$var213 has not been set already allocate space.
		if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
			// Constructor for issues$var213
			issues$var213 = new boolean[5][];
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				issues$var213[i$var211] = new boolean[6];
		}
		
		// Constructor for p13
		p13 = new double[5][];
		p13[0] = new double[2];
		p13[1] = new double[2];
		p13[2] = new double[2];
		p13[3] = new double[2];
		p13[4] = new double[2];
		
		// Constructor for n13State
		n13State = new boolean[2];
		
		// If issues$var383 has not been set already allocate space.
		if(!fixedFlag$sample430) {
			// Constructor for issues$var383
			issues$var383 = new boolean[2][];
			
			// Substituted "i$var381" with its value "0".
			issues$var383[0] = new boolean[5];
			
			// Substituted "i$var381" with its value "1".
			issues$var383[1] = new boolean[5];
		}
		
		// Constructor for constrainedFlag$sample233
		constrainedFlag$sample233 = new boolean[5];
		
		// Constructor for constrainedFlag$sample248
		constrainedFlag$sample248 = new boolean[5];
		
		// Constructor for constrainedFlag$sample263
		constrainedFlag$sample263 = new boolean[5];
		
		// Constructor for constrainedFlag$sample278
		constrainedFlag$sample278 = new boolean[5];
		
		// Constructor for constrainedFlag$sample293
		constrainedFlag$sample293 = new boolean[5];
		
		// Constructor for constrainedFlag$sample308
		constrainedFlag$sample308 = new boolean[5];
		
		// Constructor for logProbability$sample233
		logProbability$sample233 = new double[5];
		
		// Constructor for logProbability$sample248
		logProbability$sample248 = new double[5];
		
		// Constructor for logProbability$sample263
		logProbability$sample263 = new double[5];
		
		// Constructor for logProbability$sample278
		logProbability$sample278 = new double[5];
		
		// Constructor for logProbability$sample293
		logProbability$sample293 = new double[5];
		
		// Constructor for logProbability$sample308
		logProbability$sample308 = new double[5];
		
		// Constructor for logProbability$sample430
		logProbability$sample430 = new double[2][];
		
		// Substituted "i$var381" with its value "0".
		logProbability$sample430[0] = new double[5];
		
		// Substituted "i$var381" with its value "1".
		logProbability$sample430[1] = new double[5];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample233) {
				// This value is not used before it is set again, so removing the value declaration.
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample248) {
				// This value is not used before it is set again, so removing the value declaration.
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample263) {
				// This value is not used before it is set again, so removing the value declaration.
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample278) {
				// This value is not used before it is set again, so removing the value declaration.
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample293) {
				// This value is not used before it is set again, so removing the value declaration.
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample308) {
				// This value is not used before it is set again, so removing the value declaration.
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
				// Reduction of array issues
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				boolean reduceVar$var300$12 = false;
				
				// For each index in the array to be reduced
				for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// y$var298's comment
					// Set the right hand term to a value from the array issues
					reduceVar$var300$12 = (reduceVar$var300$12 || issues$var213[i$var211][cv$reduction313Index]);
				noisyOr[i$var211] = reduceVar$var300$12;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			{
				for(int j = 0; j < 5; j += 1) {
					// This value is not used before it is set again, so removing the value declaration.
					double var402;
					if(noisyOr[j])
						// Substituted "i$var381" with its value "0".
						var402 = p13[j][0];
					else
						var402 = 0.0;
					
					// Substituted "i$var381" with its value "0".
					issues$var383[0][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
				}
				
				// Reduction of array issues
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				boolean reduceVar$var414$1 = false;
				
				// For each index in the array to be reduced
				for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// x$var411's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
					// y$var412's comment
					// Set the right hand term to a value from the array issues
					// 
					// Substituted "i$var381" with its value "0".
					reduceVar$var414$1 = (reduceVar$var414$1 || issues$var383[0][cv$reduction435Index]);
				
				// Substituted "i$var381" with its value "0".
				n13State[0] = reduceVar$var414$1;
			}
			for(int j = 0; j < 5; j += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Substituted "i$var381" with its value "1".
				issues$var383[1][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$1 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "1".
				reduceVar$var414$1 = (reduceVar$var414$1 || issues$var383[1][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "1".
			n13State[1] = reduceVar$var414$1;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample233) {
				// This value is not used before it is set again, so removing the value declaration.
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample248) {
				// This value is not used before it is set again, so removing the value declaration.
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample263) {
				// This value is not used before it is set again, so removing the value declaration.
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample278) {
				// This value is not used before it is set again, so removing the value declaration.
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample293) {
				// This value is not used before it is set again, so removing the value declaration.
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample308) {
				// This value is not used before it is set again, so removing the value declaration.
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$16 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$16 = (reduceVar$var300$16 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$16;
		}
		{
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample430) {
				for(int j = 0; j < 5; j += 1) {
					// This value is not used before it is set again, so removing the value declaration.
					double var402;
					if(noisyOr[j])
						// Substituted "i$var381" with its value "0".
						var402 = p13[j][0];
					else
						var402 = 0.0;
					
					// Substituted "i$var381" with its value "0".
					issues$var383[0][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
				}
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$5 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "0".
				reduceVar$var414$5 = (reduceVar$var414$5 || issues$var383[0][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "0".
			n13State[0] = reduceVar$var414$5;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			for(int j = 0; j < 5; j += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Substituted "i$var381" with its value "1".
				issues$var383[1][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
			}
		}
		
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$5 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// x$var411's comment
			// Set the left hand term of the reduction function to the return variable value.
			// 
			// y$var412's comment
			// Set the right hand term to a value from the array issues
			// 
			// Substituted "i$var381" with its value "1".
			reduceVar$var414$5 = (reduceVar$var414$5 || issues$var383[1][cv$reduction435Index]);
		
		// Substituted "i$var381" with its value "1".
		n13State[1] = reduceVar$var414$5;
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample233) {
				// This value is not used before it is set again, so removing the value declaration.
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample248) {
				// This value is not used before it is set again, so removing the value declaration.
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample263) {
				// This value is not used before it is set again, so removing the value declaration.
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample278) {
				// This value is not used before it is set again, so removing the value declaration.
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample293) {
				// This value is not used before it is set again, so removing the value declaration.
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample308) {
				// This value is not used before it is set again, so removing the value declaration.
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$13 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$13 = (reduceVar$var300$13 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$13;
		}
		{
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample430) {
				for(int j = 0; j < 5; j += 1) {
					// This value is not used before it is set again, so removing the value declaration.
					double var402;
					if(noisyOr[j])
						// Substituted "i$var381" with its value "0".
						var402 = p13[j][0];
					else
						var402 = 0.0;
					
					// Substituted "i$var381" with its value "0".
					issues$var383[0][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
				}
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$2 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "0".
				reduceVar$var414$2 = (reduceVar$var414$2 || issues$var383[0][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "0".
			n13State[0] = reduceVar$var414$2;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			for(int j = 0; j < 5; j += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Substituted "i$var381" with its value "1".
				issues$var383[1][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
			}
		}
		
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$2 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// x$var411's comment
			// Set the left hand term of the reduction function to the return variable value.
			// 
			// y$var412's comment
			// Set the right hand term to a value from the array issues
			// 
			// Substituted "i$var381" with its value "1".
			reduceVar$var414$2 = (reduceVar$var414$2 || issues$var383[1][cv$reduction435Index]);
		
		// Substituted "i$var381" with its value "1".
		n13State[1] = reduceVar$var414$2;
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample233) {
				// This value is not used before it is set again, so removing the value declaration.
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample248) {
				// This value is not used before it is set again, so removing the value declaration.
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample263) {
				// This value is not used before it is set again, so removing the value declaration.
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample278) {
				// This value is not used before it is set again, so removing the value declaration.
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample293) {
				// This value is not used before it is set again, so removing the value declaration.
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample308) {
				// This value is not used before it is set again, so removing the value declaration.
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
				// Reduction of array issues
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				boolean reduceVar$var300$14 = false;
				
				// For each index in the array to be reduced
				for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// y$var298's comment
					// Set the right hand term to a value from the array issues
					reduceVar$var300$14 = (reduceVar$var300$14 || issues$var213[i$var211][cv$reduction313Index]);
				noisyOr[i$var211] = reduceVar$var300$14;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			{
				for(int j = 0; j < 5; j += 1) {
					// This value is not used before it is set again, so removing the value declaration.
					double var402;
					if(noisyOr[j])
						// Substituted "i$var381" with its value "0".
						var402 = p13[j][0];
					else
						var402 = 0.0;
					
					// Substituted "i$var381" with its value "0".
					issues$var383[0][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
				}
				
				// Reduction of array issues
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				boolean reduceVar$var414$3 = false;
				
				// For each index in the array to be reduced
				for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// x$var411's comment
					// Set the left hand term of the reduction function to the return variable value.
					// 
					// y$var412's comment
					// Set the right hand term to a value from the array issues
					// 
					// Substituted "i$var381" with its value "0".
					reduceVar$var414$3 = (reduceVar$var414$3 || issues$var383[0][cv$reduction435Index]);
				
				// Substituted "i$var381" with its value "0".
				n13State[0] = reduceVar$var414$3;
			}
			for(int j = 0; j < 5; j += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Substituted "i$var381" with its value "1".
				issues$var383[1][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$3 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "1".
				reduceVar$var414$3 = (reduceVar$var414$3 || issues$var383[1][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "1".
			n13State[1] = reduceVar$var414$3;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, 0.01);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample233) {
				// This value is not used before it is set again, so removing the value declaration.
				double var223;
				if(flag1)
					var223 = p[0][i$var211];
				else
					var223 = 0.0;
				issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample248) {
				// This value is not used before it is set again, so removing the value declaration.
				double var236;
				if(flag2)
					var236 = p[1][i$var211];
				else
					var236 = 0.0;
				issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample263) {
				// This value is not used before it is set again, so removing the value declaration.
				double var249;
				if(flag3)
					var249 = p[2][i$var211];
				else
					var249 = 0.0;
				issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample278) {
				// This value is not used before it is set again, so removing the value declaration.
				double var262;
				if(flag4)
					var262 = p[3][i$var211];
				else
					var262 = 0.0;
				issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample293) {
				// This value is not used before it is set again, so removing the value declaration.
				double var275;
				if(flag5)
					var275 = p[4][i$var211];
				else
					var275 = 0.0;
				issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample308) {
				// This value is not used before it is set again, so removing the value declaration.
				double var288;
				if(flag6)
					var288 = p[5][i$var211];
				else
					var288 = 0.0;
				issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$15 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$15 = (reduceVar$var300$15 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$15;
		}
		{
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample430) {
				for(int j = 0; j < 5; j += 1) {
					// This value is not used before it is set again, so removing the value declaration.
					double var402;
					if(noisyOr[j])
						// Substituted "i$var381" with its value "0".
						var402 = p13[j][0];
					else
						var402 = 0.0;
					
					// Substituted "i$var381" with its value "0".
					issues$var383[0][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
				}
			}
			
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$4 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "0".
				reduceVar$var414$4 = (reduceVar$var414$4 || issues$var383[0][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "0".
			n13State[0] = reduceVar$var414$4;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			for(int j = 0; j < 5; j += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				double var402;
				if(noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = p13[j][1];
				else
					var402 = 0.0;
				
				// Substituted "i$var381" with its value "1".
				issues$var383[1][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
			}
		}
		
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$4 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// x$var411's comment
			// Set the left hand term of the reduction function to the return variable value.
			// 
			// y$var412's comment
			// Set the right hand term to a value from the array issues
			// 
			// Substituted "i$var381" with its value "1".
			reduceVar$var414$4 = (reduceVar$var414$4 || issues$var383[1][cv$reduction435Index]);
		
		// Substituted "i$var381" with its value "1".
		n13State[1] = reduceVar$var414$4;
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample3)
				sample3();
			if(!fixedFlag$sample6)
				sample6();
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample18)
				sample18();
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if(!fixedFlag$sample233)
					sample233(i$var211);
				if(!fixedFlag$sample248)
					sample248(i$var211);
				if(!fixedFlag$sample263)
					sample263(i$var211);
				if(!fixedFlag$sample278)
					sample278(i$var211);
				if(!fixedFlag$sample293)
					sample293(i$var211);
				if(!fixedFlag$sample308)
					sample308(i$var211);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var211 = 4; i$var211 >= 0; i$var211 -= 1) {
				if(!fixedFlag$sample308)
					sample308(i$var211);
				if(!fixedFlag$sample293)
					sample293(i$var211);
				if(!fixedFlag$sample278)
					sample278(i$var211);
				if(!fixedFlag$sample263)
					sample263(i$var211);
				if(!fixedFlag$sample248)
					sample248(i$var211);
				if(!fixedFlag$sample233)
					sample233(i$var211);
			}
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample6)
				sample6();
			if(!fixedFlag$sample3)
				sample3();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample430) {
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "0".
				sample430(0, j);
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "1".
				sample430(1, j);
		}
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
		if(!fixedProbFlag$sample3)
			logProbability$flag1 = Double.NaN;
		if(!fixedProbFlag$sample6)
			logProbability$flag2 = Double.NaN;
		if(!fixedProbFlag$sample9)
			logProbability$flag3 = Double.NaN;
		if(!fixedProbFlag$sample12)
			logProbability$flag4 = Double.NaN;
		if(!fixedProbFlag$sample15)
			logProbability$flag5 = Double.NaN;
		if(!fixedProbFlag$sample18)
			logProbability$flag6 = Double.NaN;
		logProbability$issues$var213 = 0.0;
		logProbability$noisyOr = 0.0;
		if(!fixedProbFlag$sample233) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample233[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample248[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample263[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample278[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample293[i$var211] = Double.NaN;
		}
		if(!fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample308[i$var211] = Double.NaN;
		}
		logProbability$issues$var383 = 0.0;
		logProbability$n13State = 0.0;
		if(!fixedProbFlag$sample430) {
			// Unrolled loop
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "0".
				logProbability$sample430[0][j] = Double.NaN;
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "1".
				logProbability$sample430[1][j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var23 = p[0];
		var23[0] = 0.0;
		var23[1] = 1.0;
		var23[2] = 0.0;
		var23[3] = 0.0;
		var23[4] = 0.0;
		double[] var53 = p[1];
		var53[0] = 0.5;
		var53[1] = 0.5;
		var53[2] = 0.0;
		var53[3] = 0.0;
		var53[4] = 0.0;
		double[] var81 = p[2];
		var81[0] = 0.0;
		var81[1] = 0.0;
		var81[2] = 0.0;
		var81[3] = 1.0;
		var81[4] = 0.0;
		double[] var111 = p[3];
		var111[0] = 0.0;
		var111[1] = 0.0;
		var111[2] = 0.0;
		var111[3] = 1.0;
		var111[4] = 0.0;
		double[] var141 = p[4];
		var141[0] = 0.0;
		var141[1] = 0.0;
		var141[2] = 1.0;
		var141[3] = 0.0;
		var141[4] = 0.0;
		double[] var171 = p[5];
		var171[0] = 0.0;
		var171[1] = 0.0;
		var171[2] = 1.0;
		var171[3] = 0.0;
		var171[4] = 0.0;
		double[] var306 = p13[0];
		var306[0] = 0.1;
		var306[1] = 0.9;
		double[] var319 = p13[1];
		var319[0] = 1.0;
		var319[1] = 0.0;
		double[] var332 = p13[2];
		var332[0] = 0.5;
		var332[1] = 0.5;
		double[] var345 = p13[3];
		var345[0] = 0.5;
		var345[1] = 0.5;
		double[] var358 = p13[4];
		var358[0] = 0.0;
		var358[1] = 1.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
			constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
			constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
			constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
			constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
			constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
			constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample3)
			logProbabilityValue$sample3();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(fixedFlag$sample233)
			logProbabilityValue$sample233();
		if(fixedFlag$sample248)
			logProbabilityValue$sample248();
		if(fixedFlag$sample263)
			logProbabilityValue$sample263();
		if(fixedFlag$sample278)
			logProbabilityValue$sample278();
		if(fixedFlag$sample293)
			logProbabilityValue$sample293();
		if(fixedFlag$sample308)
			logProbabilityValue$sample308();
		if(fixedFlag$sample430)
			logProbabilityValue$sample430();
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
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
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
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$17 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$17 = (reduceVar$var300$17 || issues$var213[i$var211][cv$reduction313Index]);
			noisyOr[i$var211] = reduceVar$var300$17;
		}
		{
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var414$6 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// x$var411's comment
				// Set the left hand term of the reduction function to the return variable value.
				// 
				// y$var412's comment
				// Set the right hand term to a value from the array issues
				// 
				// Substituted "i$var381" with its value "0".
				reduceVar$var414$6 = (reduceVar$var414$6 || issues$var383[0][cv$reduction435Index]);
			
			// Substituted "i$var381" with its value "0".
			n13State[0] = reduceVar$var414$6;
		}
		
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$6 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// x$var411's comment
			// Set the left hand term of the reduction function to the return variable value.
			// 
			// y$var412's comment
			// Set the right hand term to a value from the array issues
			// 
			// Substituted "i$var381" with its value "1".
			reduceVar$var414$6 = (reduceVar$var414$6 || issues$var383[1][cv$reduction435Index]);
		
		// Substituted "i$var381" with its value "1".
		n13State[1] = reduceVar$var414$6;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model NoisyOr() {\n"
		     + "\n"
		     + "\n"
		     + "    // 1)\n"
		     + "    double prior1 = 0.01;\n"
		     + "    boolean flag1 = bernoulli(prior1).sample();\n"
		     + "    \n"
		     + "    // 2)\n"
		     + "    double prior2 = 0.01;\n"
		     + "    boolean flag2 = bernoulli(prior2).sample();\n"
		     + "    \n"
		     + "    // 3)\n"
		     + "    double prior3 = 0.01;\n"
		     + "    boolean flag3 = bernoulli(prior3).sample();\n"
		     + "    \n"
		     + "    // 4)\n"
		     + "    double prior4 = 0.01;\n"
		     + "    boolean flag4 = bernoulli(prior4).sample();\n"
		     + "    \n"
		     + "    // 5)\n"
		     + "    double prior5 = 0.01;\n"
		     + "    boolean flag5 = bernoulli(prior5).sample();\n"
		     + "    \n"
		     + "    // 6)\n"
		     + "    double prior6 = 0.01;\n"
		     + "    boolean flag6 = bernoulli(prior6).sample();\n"
		     + "    \n"
		     + "    // Start n12\n"
		     + "    double[][] p = new double[6][];\n"
		     + "    p[0] = new double[] {0,1,0,0,0};\n"
		     + "    p[1] = new double[] {0.5,0.5,0,0,0};\n"
		     + "    p[2] = new double[] {0,0,0,1,0};\n"
		     + "    p[3] = new double[] {0,0,0,1,0};\n"
		     + "    p[4] = new double[] {0,0,1,0,0};\n"
		     + "    p[5] = new double[] {0,0,1,0,0};\n"
		     + "    \n"
		     + "    boolean[] noisyOr = new boolean[5];\n"
		     + "    \n"
		     + "    for(int i=0; i<5; i++) {\n"
		     + "        boolean[] issues = new boolean[6];\n"
		     + "        issues[0] = bernoulli(flag1?p[0][i]:0).sample();\n"
		     + "        issues[1] = bernoulli(flag2?p[1][i]:0).sample();\n"
		     + "        issues[2] = bernoulli(flag3?p[2][i]:0).sample();\n"
		     + "        issues[3] = bernoulli(flag4?p[3][i]:0).sample();\n"
		     + "        issues[4] = bernoulli(flag5?p[4][i]:0).sample();\n"
		     + "        issues[5] = bernoulli(flag6?p[5][i]:0).sample();\n"
		     + "        \n"
		     + "        noisyOr[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Starting n13\n"
		     + "    double[][] p13 = new double[5][];\n"
		     + "    p13[0] = new double[] {0.1, 0.9};\n"
		     + "    p13[1] = new double[] {1.0, 0.0};\n"
		     + "    p13[2] = new double[] {0.5, 0.5};\n"
		     + "    p13[3] = new double[] {0.5, 0.5};\n"
		     + "    p13[4] = new double[] {0.0, 1.0};\n"
		     + "    \n"
		     + "    boolean[] n13State = new boolean[2];\n"
		     + "    \n"
		     + "    for(int i=0; i<2; i++) {\n"
		     + "        boolean[] issues = new boolean[5];\n"
		     + "        for(int j=0; j<5; j++)\n"
		     + "            issues[j] = bernoulli(noisyOr[j]?p13[j][i]:0).sample();\n"
		     + "        \n"
		     + "        n13State[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}