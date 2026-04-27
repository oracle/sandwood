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
 * Class representing the Sandwood model NoisyOr This is the class that all user interactions
 * with the model should occur through.
 */
public final class NoisyOr extends Model<NoisyOr.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean constrainedFlag$sample12 = true;
		boolean constrainedFlag$sample15 = true;
		boolean constrainedFlag$sample18 = true;
		boolean[] constrainedFlag$sample233;
		boolean[] constrainedFlag$sample248;
		boolean[] constrainedFlag$sample263;
		boolean[] constrainedFlag$sample278;
		boolean[] constrainedFlag$sample293;
		boolean constrainedFlag$sample3 = true;
		boolean[] constrainedFlag$sample308;
		boolean constrainedFlag$sample6 = true;
		boolean constrainedFlag$sample9 = true;
		boolean fixedFlag$sample12 = false;
		boolean fixedFlag$sample15 = false;
		boolean fixedFlag$sample18 = false;
		boolean fixedFlag$sample233 = false;
		boolean fixedFlag$sample248 = false;
		boolean fixedFlag$sample263 = false;
		boolean fixedFlag$sample278 = false;
		boolean fixedFlag$sample293 = false;
		boolean fixedFlag$sample3 = false;
		boolean fixedFlag$sample308 = false;
		boolean fixedFlag$sample430 = false;
		boolean fixedFlag$sample6 = false;
		boolean fixedFlag$sample9 = false;
		boolean fixedProbFlag$sample12 = false;
		boolean fixedProbFlag$sample15 = false;
		boolean fixedProbFlag$sample18 = false;
		boolean fixedProbFlag$sample233 = false;
		boolean fixedProbFlag$sample248 = false;
		boolean fixedProbFlag$sample263 = false;
		boolean fixedProbFlag$sample278 = false;
		boolean fixedProbFlag$sample293 = false;
		boolean fixedProbFlag$sample3 = false;
		boolean fixedProbFlag$sample308 = false;
		boolean fixedProbFlag$sample430 = false;
		boolean fixedProbFlag$sample6 = false;
		boolean fixedProbFlag$sample9 = false;
		boolean flag1;
		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean flag5;
		boolean flag6;
		boolean[][] issues$var213;
		boolean[][] issues$var383;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$flag1;
		double logProbability$flag2;
		double logProbability$flag3;
		double logProbability$flag4;
		double logProbability$flag5;
		double logProbability$flag6;
		double logProbability$issues$var213;
		double logProbability$issues$var383;
		double logProbability$n13State;
		double logProbability$noisyOr;
		double[] logProbability$sample233;
		double[] logProbability$sample248;
		double[] logProbability$sample263;
		double[] logProbability$sample278;
		double[] logProbability$sample293;
		double[] logProbability$sample308;
		double[][] logProbability$sample430;
		boolean[] n13State;
		boolean[] noisyOr;
		double[][] p;
		double[][] p13;
		double prior1;
		double prior2;
		double prior3;
		double prior4;
		double prior5;
		double prior6;
		boolean system$gibbsForward = true;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
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
		}

		// Getter for fixedFlag$sample12.
		final boolean get$fixedFlag$sample12() {
			return fixedFlag$sample12;
		}

		// Setter for fixedFlag$sample12.
		final void set$fixedFlag$sample12(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
			// need to be updated.
			fixedFlag$sample12 = cv$value;
			
			// Substituted "fixedFlag$sample12" with its value "cv$value".
			constrainedFlag$sample12 = (cv$value || constrainedFlag$sample12);
			
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
		final boolean get$fixedFlag$sample15() {
			return fixedFlag$sample15;
		}

		// Setter for fixedFlag$sample15.
		final void set$fixedFlag$sample15(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample15 including if probabilities
			// need to be updated.
			fixedFlag$sample15 = cv$value;
			
			// Substituted "fixedFlag$sample15" with its value "cv$value".
			constrainedFlag$sample15 = (cv$value || constrainedFlag$sample15);
			
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
		final boolean get$fixedFlag$sample18() {
			return fixedFlag$sample18;
		}

		// Setter for fixedFlag$sample18.
		final void set$fixedFlag$sample18(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample18 including if probabilities
			// need to be updated.
			fixedFlag$sample18 = cv$value;
			
			// Substituted "fixedFlag$sample18" with its value "cv$value".
			constrainedFlag$sample18 = (cv$value || constrainedFlag$sample18);
			
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
		final boolean get$fixedFlag$sample233() {
			return fixedFlag$sample233;
		}

		// Setter for fixedFlag$sample233.
		final void set$fixedFlag$sample233(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample233 including if probabilities
			// need to be updated.
			fixedFlag$sample233 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
					constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample248() {
			return fixedFlag$sample248;
		}

		// Setter for fixedFlag$sample248.
		final void set$fixedFlag$sample248(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample248 including if probabilities
			// need to be updated.
			fixedFlag$sample248 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
					constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample263() {
			return fixedFlag$sample263;
		}

		// Setter for fixedFlag$sample263.
		final void set$fixedFlag$sample263(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample263 including if probabilities
			// need to be updated.
			fixedFlag$sample263 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
					constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample278() {
			return fixedFlag$sample278;
		}

		// Setter for fixedFlag$sample278.
		final void set$fixedFlag$sample278(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample278 including if probabilities
			// need to be updated.
			fixedFlag$sample278 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
					constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample293() {
			return fixedFlag$sample293;
		}

		// Setter for fixedFlag$sample293.
		final void set$fixedFlag$sample293(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample293 including if probabilities
			// need to be updated.
			fixedFlag$sample293 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
					constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample3() {
			return fixedFlag$sample3;
		}

		// Setter for fixedFlag$sample3.
		final void set$fixedFlag$sample3(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample3 including if probabilities
			// need to be updated.
			fixedFlag$sample3 = cv$value;
			
			// Substituted "fixedFlag$sample3" with its value "cv$value".
			constrainedFlag$sample3 = (cv$value || constrainedFlag$sample3);
			
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
		final boolean get$fixedFlag$sample308() {
			return fixedFlag$sample308;
		}

		// Setter for fixedFlag$sample308.
		final void set$fixedFlag$sample308(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample308 including if probabilities
			// need to be updated.
			fixedFlag$sample308 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
					constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
			}
			
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
		final boolean get$fixedFlag$sample430() {
			return fixedFlag$sample430;
		}

		// Setter for fixedFlag$sample430.
		final void set$fixedFlag$sample430(boolean cv$value, boolean allocated$) {
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
		final boolean get$fixedFlag$sample6() {
			return fixedFlag$sample6;
		}

		// Setter for fixedFlag$sample6.
		final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample6 including if probabilities
			// need to be updated.
			fixedFlag$sample6 = cv$value;
			
			// Substituted "fixedFlag$sample6" with its value "cv$value".
			constrainedFlag$sample6 = (cv$value || constrainedFlag$sample6);
			
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
		final boolean get$fixedFlag$sample9() {
			return fixedFlag$sample9;
		}

		// Setter for fixedFlag$sample9.
		final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
			// need to be updated.
			fixedFlag$sample9 = cv$value;
			
			// Substituted "fixedFlag$sample9" with its value "cv$value".
			constrainedFlag$sample9 = (cv$value || constrainedFlag$sample9);
			
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
		final boolean get$flag1() {
			return flag1;
		}

		// Setter for flag1.
		final void set$flag1(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag1 including if probabilities need to
			// be updated.
			flag1 = cv$value;
			
			// Unset the fixed probability flag for sample 3 as it depends on flag1.
			fixedProbFlag$sample3 = false;
			
			// Unset the fixed probability flag for sample 233 as it depends on flag1.
			fixedProbFlag$sample233 = false;
		}

		// Getter for flag2.
		final boolean get$flag2() {
			return flag2;
		}

		// Setter for flag2.
		final void set$flag2(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag2 including if probabilities need to
			// be updated.
			flag2 = cv$value;
			
			// Unset the fixed probability flag for sample 6 as it depends on flag2.
			fixedProbFlag$sample6 = false;
			
			// Unset the fixed probability flag for sample 248 as it depends on flag2.
			fixedProbFlag$sample248 = false;
		}

		// Getter for flag3.
		final boolean get$flag3() {
			return flag3;
		}

		// Setter for flag3.
		final void set$flag3(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag3 including if probabilities need to
			// be updated.
			flag3 = cv$value;
			
			// Unset the fixed probability flag for sample 9 as it depends on flag3.
			fixedProbFlag$sample9 = false;
			
			// Unset the fixed probability flag for sample 263 as it depends on flag3.
			fixedProbFlag$sample263 = false;
		}

		// Getter for flag4.
		final boolean get$flag4() {
			return flag4;
		}

		// Setter for flag4.
		final void set$flag4(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag4 including if probabilities need to
			// be updated.
			flag4 = cv$value;
			
			// Unset the fixed probability flag for sample 12 as it depends on flag4.
			fixedProbFlag$sample12 = false;
			
			// Unset the fixed probability flag for sample 278 as it depends on flag4.
			fixedProbFlag$sample278 = false;
		}

		// Getter for flag5.
		final boolean get$flag5() {
			return flag5;
		}

		// Setter for flag5.
		final void set$flag5(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag5 including if probabilities need to
			// be updated.
			flag5 = cv$value;
			
			// Unset the fixed probability flag for sample 15 as it depends on flag5.
			fixedProbFlag$sample15 = false;
			
			// Unset the fixed probability flag for sample 293 as it depends on flag5.
			fixedProbFlag$sample293 = false;
		}

		// Getter for flag6.
		final boolean get$flag6() {
			return flag6;
		}

		// Setter for flag6.
		final void set$flag6(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of flag6 including if probabilities need to
			// be updated.
			flag6 = cv$value;
			
			// Unset the fixed probability flag for sample 18 as it depends on flag6.
			fixedProbFlag$sample18 = false;
			
			// Unset the fixed probability flag for sample 308 as it depends on flag6.
			fixedProbFlag$sample308 = false;
		}

		// Getter for issues$var213.
		final boolean[][] get$issues$var213() {
			return issues$var213;
		}

		// Setter for issues$var213.
		final void set$issues$var213(boolean[][] cv$value, boolean allocated$) {
			issues$var213 = cv$value;
		}

		// Getter for issues$var383.
		final boolean[][] get$issues$var383() {
			return issues$var383;
		}

		// Setter for issues$var383.
		final void set$issues$var383(boolean[][] cv$value, boolean allocated$) {
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
		final double get$logProbability$flag1() {
			return logProbability$flag1;
		}

		// Getter for logProbability$flag2.
		final double get$logProbability$flag2() {
			return logProbability$flag2;
		}

		// Getter for logProbability$flag3.
		final double get$logProbability$flag3() {
			return logProbability$flag3;
		}

		// Getter for logProbability$flag4.
		final double get$logProbability$flag4() {
			return logProbability$flag4;
		}

		// Getter for logProbability$flag5.
		final double get$logProbability$flag5() {
			return logProbability$flag5;
		}

		// Getter for logProbability$flag6.
		final double get$logProbability$flag6() {
			return logProbability$flag6;
		}

		// Getter for logProbability$n13State.
		final double get$logProbability$n13State() {
			return logProbability$n13State;
		}

		// Getter for logProbability$noisyOr.
		final double get$logProbability$noisyOr() {
			return logProbability$noisyOr;
		}

		// Getter for n13State.
		final boolean[] get$n13State() {
			return n13State;
		}

		// Getter for noisyOr.
		final boolean[] get$noisyOr() {
			return noisyOr;
		}

		// Getter for p.
		final double[][] get$p() {
			return p;
		}

		// Getter for p13.
		final double[][] get$p13() {
			return p13;
		}

		// Getter for prior1.
		final double get$prior1() {
			return 0.01;
		}

		// Getter for prior2.
		final double get$prior2() {
			return 0.01;
		}

		// Getter for prior3.
		final double get$prior3() {
			return 0.01;
		}

		// Getter for prior4.
		final double get$prior4() {
			return 0.01;
		}

		// Getter for prior5.
		final double get$prior5() {
			return 0.01;
		}

		// Getter for prior6.
		final double get$prior6() {
			return 0.01;
		}
	}

    private final ComputedBooleanInternal $flag1 = new ComputedBooleanInternal(this, "flag1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag1(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample3(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample3())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag1 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag1 = $flag1;

    private final ComputedBooleanInternal $flag2 = new ComputedBooleanInternal(this, "flag2", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag2(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag2(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample6(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample6())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag2 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag2 = $flag2;

    private final ComputedBooleanInternal $flag3 = new ComputedBooleanInternal(this, "flag3", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag3(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag3(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag3(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample9(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample9())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag3 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag3 = $flag3;

    private final ComputedBooleanInternal $flag4 = new ComputedBooleanInternal(this, "flag4", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag4(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag4(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag4(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample12(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample12())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag4 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag4 = $flag4;

    private final ComputedBooleanInternal $flag5 = new ComputedBooleanInternal(this, "flag5", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag5(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag5(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag5(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample15(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample15())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag5 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag5 = $flag5;

    private final ComputedBooleanInternal $flag6 = new ComputedBooleanInternal(this, "flag6", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flag6(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$flag6(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flag6(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample18(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample18())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing flag6 of type boolean from the Sandwood model. */
    public final ComputedBoolean flag6 = $flag6;

    private final ComputedObjectArrayInternal<boolean[]> $issues$var213 = new ComputedObjectArrayInternal<boolean[]>(this, "issues$var213", true, true, true, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return state.get$issues$var213(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            state.set$issues$var213(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

    private final ComputedObjectArrayInternal<boolean[]> $issues$var383 = new ComputedObjectArrayInternal<boolean[]>(this, "issues$var383", true, true, true, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return state.get$issues$var383(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            state.set$issues$var383(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

    private final ComputedBooleanArrayInternal $n13State = new ComputedBooleanArrayInternal(this, "n13State", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$n13State(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable n13State because its value depends on variable \"issues$var383\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$n13State(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample430(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample430())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing n13State of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray n13State = $n13State;

    private final ComputedBooleanArrayInternal $noisyOr = new ComputedBooleanArrayInternal(this, "noisyOr", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$noisyOr(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable noisyOr because its value depends on variable \"issues$var213\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$noisyOr(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample233(fixed, allocated);
                state.set$fixedFlag$sample248(fixed, allocated);
                state.set$fixedFlag$sample263(fixed, allocated);
                state.set$fixedFlag$sample278(fixed, allocated);
                state.set$fixedFlag$sample293(fixed, allocated);
                state.set$fixedFlag$sample308(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample233 = state.get$fixedFlag$sample233();
            boolean fixedFlag$sample248 = state.get$fixedFlag$sample248();
            boolean fixedFlag$sample263 = state.get$fixedFlag$sample263();
            boolean fixedFlag$sample278 = state.get$fixedFlag$sample278();
            boolean fixedFlag$sample293 = state.get$fixedFlag$sample293();
            boolean fixedFlag$sample308 = state.get$fixedFlag$sample308();
            if(fixedFlag$sample233 && fixedFlag$sample248 && fixedFlag$sample263 && fixedFlag$sample278 && fixedFlag$sample293 && fixedFlag$sample308)
                return Immutability.FIXED;
            else if(fixedFlag$sample233 || fixedFlag$sample248 || fixedFlag$sample263 || fixedFlag$sample278 || fixedFlag$sample293 || fixedFlag$sample308)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing noisyOr of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray noisyOr = $noisyOr;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$flag1, $flag2, $flag3, $flag4, $flag5, $flag6, $n13State, $noisyOr};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public NoisyOr() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("flag1", $flag1);
        $computedVariables.put("flag2", $flag2);
        $computedVariables.put("flag3", $flag3);
        $computedVariables.put("flag4", $flag4);
        $computedVariables.put("flag5", $flag5);
        $computedVariables.put("flag6", $flag6);
        $computedVariables.put("issues$var213", $issues$var213);
        $computedVariables.put("issues$var383", $issues$var383);
        $computedVariables.put("n13State", $n13State);
        $computedVariables.put("noisyOr", $noisyOr);

        NoisyOr$SingleThreadCPU core = new NoisyOr$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new NoisyOr$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new NoisyOr$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 */
        public InferValueInputs() {
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 */
        public AllInputs() {
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of flag1 after a convention execution step. */
        public final boolean flag1;
		/** Field holding the value of flag2 after a convention execution step. */
        public final boolean flag2;
		/** Field holding the value of flag3 after a convention execution step. */
        public final boolean flag3;
		/** Field holding the value of flag4 after a convention execution step. */
        public final boolean flag4;
		/** Field holding the value of flag5 after a convention execution step. */
        public final boolean flag5;
		/** Field holding the value of flag6 after a convention execution step. */
        public final boolean flag6;
		/** Field holding the value of n13State after a convention execution step. */
        public final boolean[] n13State;
		/** Field holding the value of noisyOr after a convention execution step. */
        public final boolean[] noisyOr;

        InferredValueOutputs(NoisyOr system$model) {
            this.flag1 = system$model.flag1.getSamples()[0];
            this.flag2 = system$model.flag2.getSamples()[0];
            this.flag3 = system$model.flag3.getSamples()[0];
            this.flag4 = system$model.flag4.getSamples()[0];
            this.flag5 = system$model.flag5.getSamples()[0];
            this.flag6 = system$model.flag6.getSamples()[0];
            this.n13State = system$model.n13State.getSamples()[0];
            this.noisyOr = system$model.noisyOr.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable flag1 */
        public final double flag1;
		/** Field holding the log probability of computed variable flag2 */
        public final double flag2;
		/** Field holding the log probability of computed variable flag3 */
        public final double flag3;
		/** Field holding the log probability of computed variable flag4 */
        public final double flag4;
		/** Field holding the log probability of computed variable flag5 */
        public final double flag5;
		/** Field holding the log probability of computed variable flag6 */
        public final double flag6;
		/** Field holding the log probability of computed variable n13State */
        public final double n13State;
		/** Field holding the log probability of computed variable noisyOr */
        public final double noisyOr;

        LogProbabilities(NoisyOr system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.flag1 = system$model.flag1.getLogProbability();
            this.flag2 = system$model.flag2.getLogProbability();
            this.flag3 = system$model.flag3.getLogProbability();
            this.flag4 = system$model.flag4.getLogProbability();
            this.flag5 = system$model.flag5.getLogProbability();
            this.flag6 = system$model.flag6.getLogProbability();
            this.n13State = system$model.n13State.getLogProbability();
            this.noisyOr = system$model.noisyOr.getLogProbability();
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
		/** Field holding the probability of computed variable flag1 */
        public final double flag1;
		/** Field holding the probability of computed variable flag2 */
        public final double flag2;
		/** Field holding the probability of computed variable flag3 */
        public final double flag3;
		/** Field holding the probability of computed variable flag4 */
        public final double flag4;
		/** Field holding the probability of computed variable flag5 */
        public final double flag5;
		/** Field holding the probability of computed variable flag6 */
        public final double flag6;
		/** Field holding the probability of computed variable n13State */
        public final double n13State;
		/** Field holding the probability of computed variable noisyOr */
        public final double noisyOr;

        Probabilities(NoisyOr system$model) {
            this.$modelProbability = system$model.getProbability();
            this.flag1 = system$model.flag1.getProbability();
            this.flag2 = system$model.flag2.getProbability();
            this.flag3 = system$model.flag3.getProbability();
            this.flag4 = system$model.flag4.getProbability();
            this.flag5 = system$model.flag5.getProbability();
            this.flag6 = system$model.flag6.getProbability();
            this.n13State = system$model.n13State.getProbability();
            this.noisyOr = system$model.noisyOr.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of flag1 after an infer model call. */
        public final boolean[] flag1;
		/** Field holding the MAP or Sample value of flag2 after an infer model call. */
        public final boolean[] flag2;
		/** Field holding the MAP or Sample value of flag3 after an infer model call. */
        public final boolean[] flag3;
		/** Field holding the MAP or Sample value of flag4 after an infer model call. */
        public final boolean[] flag4;
		/** Field holding the MAP or Sample value of flag5 after an infer model call. */
        public final boolean[] flag5;
		/** Field holding the MAP or Sample value of flag6 after an infer model call. */
        public final boolean[] flag6;
		/** Field holding the MAP or Sample value of n13State after an infer model call. */
        public final boolean[][] n13State;
		/** Field holding the MAP or Sample value of noisyOr after an infer model call. */
        public final boolean[][] noisyOr;

        InferredModelOutputs(NoisyOr system$model) {
            this.flag1 = system$model.getInferredValue(system$model.$flag1);
            this.flag2 = system$model.getInferredValue(system$model.$flag2);
            this.flag3 = system$model.getInferredValue(system$model.$flag3);
            this.flag4 = system$model.getInferredValue(system$model.$flag4);
            this.flag5 = system$model.getInferredValue(system$model.$flag5);
            this.flag6 = system$model.getInferredValue(system$model.$flag6);
            this.n13State = system$model.getInferredValue(system$model.$n13State);
            this.noisyOr = system$model.getInferredValue(system$model.$noisyOr);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
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
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}