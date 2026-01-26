package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements NoisyOr$CoreInterface {
	
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
	private double[][] cv$var225$stateProbabilityGlobal;
	private double[][] cv$var238$stateProbabilityGlobal;
	private double[][] cv$var251$stateProbabilityGlobal;
	private double[][] cv$var264$stateProbabilityGlobal;
	private double[][] cv$var277$stateProbabilityGlobal;
	private double[][] cv$var290$stateProbabilityGlobal;
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
	private double prior1;
	private double prior2;
	private double prior3;
	private double prior4;
	private double prior5;
	private double prior6;
	private boolean system$gibbsForward = true;

	public NoisyOr$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		
		// Should the probability of sample 278 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample278 = (fixedFlag$sample12 && fixedProbFlag$sample278);
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
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		
		// Should the probability of sample 293 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample293 = (fixedFlag$sample15 && fixedProbFlag$sample293);
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
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		
		// Should the probability of sample 308 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample308 = (fixedFlag$sample18 && fixedProbFlag$sample308);
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
		fixedProbFlag$sample233 = (fixedFlag$sample233 && fixedProbFlag$sample233);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample233 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample248 = (fixedFlag$sample248 && fixedProbFlag$sample248);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample248 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample263 = (fixedFlag$sample263 && fixedProbFlag$sample263);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample263 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample278 = (fixedFlag$sample278 && fixedProbFlag$sample278);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample278 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample293 = (fixedFlag$sample293 && fixedProbFlag$sample293);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample293 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample3 = (fixedFlag$sample3 && fixedProbFlag$sample3);
		
		// Should the probability of sample 233 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample233 = (fixedFlag$sample3 && fixedProbFlag$sample233);
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
		fixedProbFlag$sample308 = (fixedFlag$sample308 && fixedProbFlag$sample308);
		
		// Should the probability of sample 430 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample430 = (fixedFlag$sample308 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample430 = (fixedFlag$sample430 && fixedProbFlag$sample430);
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
		fixedProbFlag$sample6 = (fixedFlag$sample6 && fixedProbFlag$sample6);
		
		// Should the probability of sample 248 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample248 = (fixedFlag$sample6 && fixedProbFlag$sample248);
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
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		
		// Should the probability of sample 263 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample263 = (fixedFlag$sample9 && fixedProbFlag$sample263);
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
		return prior1;
	}

	// Getter for prior2.
	@Override
	public final double get$prior2() {
		return prior2;
	}

	// Getter for prior3.
	@Override
	public final double get$prior3() {
		return prior3;
	}

	// Getter for prior4.
	@Override
	public final double get$prior4() {
		return prior4;
	}

	// Getter for prior5.
	@Override
	public final double get$prior5() {
		return prior5;
	}

	// Getter for prior6.
	@Override
	public final double get$prior6() {
		return prior6;
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag4;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior4) && (prior4 <= 1.0))?Math.log((cv$sampleValue?prior4:(1.0 - prior4))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag4 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag4;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample15 using sampled
	// values.
	private final void logProbabilityValue$sample15() {
		// Determine if we need to calculate the values for sample task 15 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample15) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag5;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior5) && (prior5 <= 1.0))?Math.log((cv$sampleValue?prior5:(1.0 - prior5))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag5 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag5;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag6;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior6) && (prior6 <= 1.0))?Math.log((cv$sampleValue?prior6:(1.0 - prior6))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag6 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][0];
						{
							{
								double var223;
								if(flag1)
									var223 = p[0][i$var211];
								else
									var223 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$sampleValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample233[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample233[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][1];
						{
							{
								double var236;
								if(flag2)
									var236 = p[1][i$var211];
								else
									var236 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$sampleValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample248[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample248[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][2];
						{
							{
								double var249;
								if(flag3)
									var249 = p[2][i$var211];
								else
									var249 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$sampleValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample263[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample263[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][3];
						{
							{
								double var262;
								if(flag4)
									var262 = p[3][i$var211];
								else
									var262 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$sampleValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample278[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample278[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][4];
						{
							{
								double var275;
								if(flag5)
									var275 = p[4][i$var211];
								else
									var275 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$sampleValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample293[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample293[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag1;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior1) && (prior1 <= 1.0))?Math.log((cv$sampleValue?prior1:(1.0 - prior1))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag1 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample3 = fixedFlag$sample3;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample3)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = issues$var213[((i$var211 - 0) / 1)][5];
						{
							{
								double var288;
								if(flag6)
									var288 = p[5][i$var211];
								else
									var288 = 0.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$sampleValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample308[((i$var211 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample308[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that noisyOr is only updated once for this probability.
				boolean cv$guard$noisyOr = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$noisyOr) {
								// Set the guard so the update is only applied once.
								cv$guard$noisyOr = true;
								
								// Update the variable probability
								logProbability$noisyOr = (logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = issues$var383[((i$var381 - 0) / 1)][j];
							{
								{
									double var402;
									if(noisyOr[j])
										var402 = p13[j][i$var381];
									else
										var402 = 0.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var402) && (var402 <= 1.0))?Math.log((cv$sampleValue?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY));
									
									// Add the probability of this sample task to the distribution accumulator.
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										// If the second value is -infinity.
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									
									// Add the probability of this distribution configuration to the accumulator.
									cv$probabilityReached = (cv$probabilityReached + 1.0);
								}
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					
					// Store the sample task probability
					logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that n13State is only updated once for this probability.
					boolean cv$guard$n13State = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						{
							if(((0 <= j) && (j < 5))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$n13State) {
									// Set the guard so the update is only applied once.
									cv$guard$n13State = true;
									
									// Update the variable probability
									logProbability$n13State = (logProbability$n13State + cv$sampleProbability);
								}
							}
						}
					}
				}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					
					// Guard to ensure that n13State is only updated once for this probability.
					boolean cv$guard$n13State = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						{
							if(((0 <= j) && (j < 5))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$n13State) {
									// Set the guard so the update is only applied once.
									cv$guard$n13State = true;
									
									// Update the variable probability
									logProbability$n13State = (logProbability$n13State + cv$sampleValue);
								}
							}
						}
					}
				}
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag2;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior2) && (prior2 <= 1.0))?Math.log((cv$sampleValue?prior2:(1.0 - prior2))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag2 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flag3;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= prior3) && (prior3 <= 1.0))?Math.log((cv$sampleValue?prior3:(1.0 - prior3))):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$flag3 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flag3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from Bernoulli 11. Inference was performed using variable
	// marginalization.
	private final void sample12() {
		if(true) {
			constrainedFlag$sample12 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var12$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag4 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior4) && (prior4 <= 1.0))?Math.log((cv$currentValue?prior4:(1.0 - prior4))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point276.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var262$2_1 = p[3][i$var211];
												
												// Processing sample task 278 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample278 || constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample12 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 278 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 278 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var262$5_1 = 0.0;
												
												// Processing sample task 278 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample278 || constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample12 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 278 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 278 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample12) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag4 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 15 drawn from Bernoulli 14. Inference was performed using variable
	// marginalization.
	private final void sample15() {
		if(true) {
			constrainedFlag$sample15 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var15$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag5 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior5) && (prior5 <= 1.0))?Math.log((cv$currentValue?prior5:(1.0 - prior5))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point291.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var275$2_1 = p[4][i$var211];
												
												// Processing sample task 293 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample293 || constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample15 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 293 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 293 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var275$5_1 = 0.0;
												
												// Processing sample task 293 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample293 || constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample15 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 293 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 293 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample15) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag5 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Bernoulli 17. Inference was performed using variable
	// marginalization.
	private final void sample18() {
		if(true) {
			constrainedFlag$sample18 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var18$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag6 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior6) && (prior6 <= 1.0))?Math.log((cv$currentValue?prior6:(1.0 - prior6))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point306.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var288$2_1 = p[5][i$var211];
												
												// Processing sample task 308 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample308 || constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample18 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 308 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 308 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var288$5_1 = 0.0;
												
												// Processing sample task 308 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample308 || constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample18 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 308 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 308 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample18) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag6 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 233 drawn from Bernoulli 224. Inference was performed using variable
	// marginalization.
	private final void sample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample233[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var225$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var225 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][0] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$18 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$18;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$18 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$18;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var223;
					if(flag1)
						var223 = p[0][i$var211];
					else
						var223 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$currentValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 233 and consumer double 402.
						{
							{
								if(((0 <= 0) && (0 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample233[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var225 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][0] = var225;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$19 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$19;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$19 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$19;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 248 drawn from Bernoulli 237. Inference was performed using variable
	// marginalization.
	private final void sample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample248[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var238$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var238 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][1] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$20 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$20;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$20 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$20;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var236;
					if(flag2)
						var236 = p[1][i$var211];
					else
						var236 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$currentValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 248 and consumer double 402.
						{
							{
								if(((0 <= 1) && (1 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample248[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var238 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][1] = var238;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$21 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$21;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$21 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$21;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 263 drawn from Bernoulli 250. Inference was performed using variable
	// marginalization.
	private final void sample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample263[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var251$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var251 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][2] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$22 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$22;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$22 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$22;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var249;
					if(flag3)
						var249 = p[2][i$var211];
					else
						var249 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$currentValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 263 and consumer double 402.
						{
							{
								if(((0 <= 2) && (2 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample263[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var251 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][2] = var251;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$23 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$23;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$23 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$23;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 278 drawn from Bernoulli 263. Inference was performed using variable
	// marginalization.
	private final void sample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample278[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var264$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var264 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][3] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$24 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$24;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$24 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$24;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var262;
					if(flag4)
						var262 = p[3][i$var211];
					else
						var262 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$currentValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 278 and consumer double 402.
						{
							{
								if(((0 <= 3) && (3 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample278[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var264 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][3] = var264;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$25 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$25;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$25 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$25;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 293 drawn from Bernoulli 276. Inference was performed using variable
	// marginalization.
	private final void sample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample293[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var277$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var277 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][4] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$26 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$26;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$26 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$26;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var275;
					if(flag5)
						var275 = p[4][i$var211];
					else
						var275 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$currentValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 293 and consumer double 402.
						{
							{
								if(((0 <= 4) && (4 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample293[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var277 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][4] = var277;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$27 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$27;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$27 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$27;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 3 drawn from Bernoulli 2. Inference was performed using variable
	// marginalization.
	private final void sample3() {
		if(true) {
			constrainedFlag$sample3 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var3$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag1 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior1) && (prior1 <= 1.0))?Math.log((cv$currentValue?prior1:(1.0 - prior1))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point231.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var223$2_1 = p[0][i$var211];
												
												// Processing sample task 233 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample233 || constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample3 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 233 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 233 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var223$5_1 = 0.0;
												
												// Processing sample task 233 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample233 || constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample3 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 233 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 233 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample3) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag1 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 308 drawn from Bernoulli 289. Inference was performed using variable
	// marginalization.
	private final void sample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		if(true) {
			constrainedFlag$sample308[((i$var211 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var290$stateProbabilityGlobal[threadID$cv$i$var211];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var290 = cv$currentValue;
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][5] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$28 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$28;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$28 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$28;
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var288;
					if(flag6)
						var288 = p[5][i$var211];
					else
						var288 = 0.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$currentValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point428.
					{
						// Looking for a path between Sample 308 and consumer double 402.
						{
							{
								if(((0 <= 5) && (5 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(noisyOr[j]) {
															double traceTempVariable$var402$4_1 = p13[j][i$var381];
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
														if(!noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															
															// Processing sample task 430 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								// Record the probability of sample task 430 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 430 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// A check to ensure rounding of floating point values can never result in a negative
																		// value.
																		cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																		
																		// Multiply (log space add) in the probability of the sample task to the overall probability
																		// for this configuration of the source random variable.
																		if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																		else {
																			// If the second value is -infinity.
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
								}
							}
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample308[((i$var211 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var290 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that issues$var213 is only updated when there is a valid path.
				{
					{
						{
							issues$var213[((i$var211 - 0) / 1)][5] = var290;
						}
					}
				}
				
				// Guards to ensure that noisyOr is only updated when there is a valid path.
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								// Reduction of array issues
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								boolean reduceVar$var300$29 = false;
								
								// For each index in the array to be reduced
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									boolean x$var297 = reduceVar$var300$29;
									
									// Set the right hand term to a value from the array issues
									boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var300$29 = (x$var297 || y$var298);
								}
								noisyOr[i$var211] = reduceVar$var300$29;
							}
						}
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample430
	private final void sample430(int i$var381, int j, int threadID$cv$j, Rng RNG$) {
		double var402;
		if(noisyOr[j])
			var402 = p13[j][i$var381];
		else
			var402 = 0.0;
		issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		
		// Guards to ensure that n13State is only updated when there is a valid path.
		{
			{
				if(((0 <= j) && (j < 5))) {
					{
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$7 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$7;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var414$7 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$7;
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Bernoulli 5. Inference was performed using variable
	// marginalization.
	private final void sample6() {
		if(true) {
			constrainedFlag$sample6 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var6$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag2 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior2) && (prior2 <= 1.0))?Math.log((cv$currentValue?prior2:(1.0 - prior2))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point246.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var236$2_1 = p[1][i$var211];
												
												// Processing sample task 248 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample248 || constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample6 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 248 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 248 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var236$5_1 = 0.0;
												
												// Processing sample task 248 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample248 || constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample6 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 248 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 248 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample6) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag2 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Bernoulli 8. Inference was performed using variable
	// marginalization.
	private final void sample9() {
		if(true) {
			constrainedFlag$sample9 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				flag3 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= prior3) && (prior3 <= 1.0))?Math.log((cv$currentValue?prior3:(1.0 - prior3))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point261.
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var249$2_1 = p[2][i$var211];
												
												// Processing sample task 263 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample263 || constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample9 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 263 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 263 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
											if(!cv$currentValue) {
												double traceTempVariable$var249$5_1 = 0.0;
												
												// Processing sample task 263 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample263 || constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample9 = true;
															
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					// Record the probability of sample task 263 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 263 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
															
															// A check to ensure rounding of floating point values can never result in a negative
															// value.
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															
															// Multiply (log space add) in the probability of the sample task to the overall probability
															// for this configuration of the source random variable.
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																// If the second value is -infinity.
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
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample9) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				flag3 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var3$stateProbabilityGlobal
		{
			// Allocation of cv$var3$stateProbabilityGlobal for single threaded execution
			cv$var3$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var6$stateProbabilityGlobal
		{
			// Allocation of cv$var6$stateProbabilityGlobal for single threaded execution
			cv$var6$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var9$stateProbabilityGlobal
		{
			// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
			cv$var9$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var12$stateProbabilityGlobal
		{
			// Allocation of cv$var12$stateProbabilityGlobal for single threaded execution
			cv$var12$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var15$stateProbabilityGlobal
		{
			// Allocation of cv$var15$stateProbabilityGlobal for single threaded execution
			cv$var15$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var18$stateProbabilityGlobal
		{
			// Allocation of cv$var18$stateProbabilityGlobal for single threaded execution
			cv$var18$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var225$stateProbabilityGlobal
		{
			// Allocation of cv$var225$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var225$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var225$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		
		// Constructor for cv$var238$stateProbabilityGlobal
		{
			// Allocation of cv$var238$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var238$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var238$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		
		// Constructor for cv$var251$stateProbabilityGlobal
		{
			// Allocation of cv$var251$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var251$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var251$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		
		// Constructor for cv$var264$stateProbabilityGlobal
		{
			// Allocation of cv$var264$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var264$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var264$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		
		// Constructor for cv$var277$stateProbabilityGlobal
		{
			// Allocation of cv$var277$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var277$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var277$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
		
		// Constructor for cv$var290$stateProbabilityGlobal
		{
			// Allocation of cv$var290$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var290$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var290$stateProbabilityGlobal[cv$index] = new double[2];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for p
		{
			p = new double[6][];
			p[0] = new double[5];
			p[1] = new double[5];
			p[2] = new double[5];
			p[3] = new double[5];
			p[4] = new double[5];
			p[5] = new double[5];
		}
		
		// Constructor for noisyOr
		{
			noisyOr = new boolean[5];
		}
		
		// If issues$var213 has not been set already allocate space.
		if((((((!fixedFlag$sample233 || !fixedFlag$sample248) || !fixedFlag$sample263) || !fixedFlag$sample278) || !fixedFlag$sample293) || !fixedFlag$sample308)) {
			// Constructor for issues$var213
			{
				issues$var213 = new boolean[((((5 - 1) - 0) / 1) + 1)][];
				for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
					issues$var213[((i$var211 - 0) / 1)] = new boolean[6];
			}
		}
		
		// Constructor for p13
		{
			p13 = new double[5][];
			p13[0] = new double[2];
			p13[1] = new double[2];
			p13[2] = new double[2];
			p13[3] = new double[2];
			p13[4] = new double[2];
		}
		
		// Constructor for n13State
		{
			n13State = new boolean[2];
		}
		
		// If issues$var383 has not been set already allocate space.
		if(!fixedFlag$sample430) {
			// Constructor for issues$var383
			{
				issues$var383 = new boolean[((((2 - 1) - 0) / 1) + 1)][];
				for(int i$var381 = 0; i$var381 < 2; i$var381 += 1)
					issues$var383[((i$var381 - 0) / 1)] = new boolean[5];
			}
		}
		
		// Constructor for constrainedFlag$sample233
		{
			constrainedFlag$sample233 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample248
		{
			constrainedFlag$sample248 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample263
		{
			constrainedFlag$sample263 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample278
		{
			constrainedFlag$sample278 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample293
		{
			constrainedFlag$sample293 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample308
		{
			constrainedFlag$sample308 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample233
		{
			logProbability$sample233 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample248
		{
			logProbability$sample248 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample263
		{
			logProbability$sample263 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample278
		{
			logProbability$sample278 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample293
		{
			logProbability$sample293 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample308
		{
			logProbability$sample308 = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample430
		{
			logProbability$sample430 = new double[((((2 - 1) - 0) / 1) + 1)][];
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1)
				logProbability$sample430[((i$var381 - 0) / 1)] = new double[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$30 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$30;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var300$30 = (x$var297 || y$var298);
						}
						if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
							noisyOr[i$var211] = reduceVar$var300$30;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$8 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$8;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample430)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var414$8 = (x$var411 || y$var412);
						}
						if(!fixedFlag$sample430)
							n13State[i$var381] = reduceVar$var414$8;
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$34 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$34;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var300$34 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$34;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$12 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$12;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var414$12 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$12;
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$31 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$31;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var300$31 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$31;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$9 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$9;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var414$9 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$9;
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$32 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$32;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var300$32 = (x$var297 || y$var298);
						}
						if(!(((((fixedFlag$sample233 && fixedFlag$sample248) && fixedFlag$sample263) && fixedFlag$sample278) && fixedFlag$sample293) && fixedFlag$sample308))
							noisyOr[i$var211] = reduceVar$var300$32;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$10 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$10;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!fixedFlag$sample430)
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var414$10 = (x$var411 || y$var412);
						}
						if(!fixedFlag$sample430)
							n13State[i$var381] = reduceVar$var414$10;
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample3)
			flag1 = DistributionSampling.sampleBernoulli(RNG$, prior1);
		if(!fixedFlag$sample6)
			flag2 = DistributionSampling.sampleBernoulli(RNG$, prior2);
		if(!fixedFlag$sample9)
			flag3 = DistributionSampling.sampleBernoulli(RNG$, prior3);
		if(!fixedFlag$sample12)
			flag4 = DistributionSampling.sampleBernoulli(RNG$, prior4);
		if(!fixedFlag$sample15)
			flag5 = DistributionSampling.sampleBernoulli(RNG$, prior5);
		if(!fixedFlag$sample18)
			flag6 = DistributionSampling.sampleBernoulli(RNG$, prior6);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						double var223 = 0.0;
						if(flag1) {
							if(!fixedFlag$sample233)
								var223 = p[0][i$var211];
						} else {
							if(!fixedFlag$sample233)
								var223 = 0.0;
						}
						if(!fixedFlag$sample233)
							issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						double var236 = 0.0;
						if(flag2) {
							if(!fixedFlag$sample248)
								var236 = p[1][i$var211];
						} else {
							if(!fixedFlag$sample248)
								var236 = 0.0;
						}
						if(!fixedFlag$sample248)
							issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						double var249 = 0.0;
						if(flag3) {
							if(!fixedFlag$sample263)
								var249 = p[2][i$var211];
						} else {
							if(!fixedFlag$sample263)
								var249 = 0.0;
						}
						if(!fixedFlag$sample263)
							issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						double var262 = 0.0;
						if(flag4) {
							if(!fixedFlag$sample278)
								var262 = p[3][i$var211];
						} else {
							if(!fixedFlag$sample278)
								var262 = 0.0;
						}
						if(!fixedFlag$sample278)
							issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						double var275 = 0.0;
						if(flag5) {
							if(!fixedFlag$sample293)
								var275 = p[4][i$var211];
						} else {
							if(!fixedFlag$sample293)
								var275 = 0.0;
						}
						if(!fixedFlag$sample293)
							issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						double var288 = 0.0;
						if(flag6) {
							if(!fixedFlag$sample308)
								var288 = p[5][i$var211];
						} else {
							if(!fixedFlag$sample308)
								var288 = 0.0;
						}
						if(!fixedFlag$sample308)
							issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$33 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$33;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var300$33 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$33;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										double var402 = 0.0;
										if(noisyOr[j]) {
											if(!fixedFlag$sample430)
												var402 = p13[j][i$var381];
										} else {
											if(!fixedFlag$sample430)
												var402 = 0.0;
										}
										if(!fixedFlag$sample430)
											issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
									}
							}
						);
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$11 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$11;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var414$11 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$11;
					}
			}
		);
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
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!fixedFlag$sample233)
								sample233(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample248)
								sample248(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample263)
								sample263(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample278)
								sample278(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample293)
								sample293(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample308)
								sample308(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!fixedFlag$sample308)
								sample308(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample293)
								sample293(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample278)
								sample278(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample263)
								sample263(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample248)
								sample248(i$var211, threadID$i$var211, RNG$1);
							if(!fixedFlag$sample233)
								sample233(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
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
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, 5, 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample430)
											sample430(i$var381, j, threadID$j, RNG$2);
									}
							}
						);
					}
			}
		);
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
				logProbability$sample233[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample248[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample263[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample278[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample293[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				logProbability$sample308[((i$var211 - 0) / 1)] = Double.NaN;
		}
		logProbability$issues$var383 = 0.0;
		logProbability$n13State = 0.0;
		if(!fixedProbFlag$sample430) {
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1)
					logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		prior1 = 0.01;
		prior2 = 0.01;
		prior3 = 0.01;
		prior4 = 0.01;
		prior5 = 0.01;
		prior6 = 0.01;
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$35 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var297 = reduceVar$var300$35;
							
							// Set the right hand term to a value from the array issues
							boolean y$var298 = issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var300$35 = (x$var297 || y$var298);
						}
						noisyOr[i$var211] = reduceVar$var300$35;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var381, int forEnd$i$var381, int threadID$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var381 = forStart$i$var381; i$var381 < forEnd$i$var381; i$var381 += 1) {
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$13 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							boolean x$var411 = reduceVar$var414$13;
							
							// Set the right hand term to a value from the array issues
							boolean y$var412 = issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var414$13 = (x$var411 || y$var412);
						}
						n13State[i$var381] = reduceVar$var414$13;
					}
			}
		);
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