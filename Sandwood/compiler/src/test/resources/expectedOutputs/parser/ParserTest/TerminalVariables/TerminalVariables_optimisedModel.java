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
 * Class representing the Sandwood model TerminalVariables This is the class that
 * all user interactions with the model should occur through.
 */
public final class TerminalVariables extends Model<TerminalVariables.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[][][][][] a;
		int c1;
		int c10;
		int c11;
		int c12;
		int c2;
		int c3;
		int c4;
		int c5;
		int c6;
		int c7;
		int c8;
		int c9;
		double[][] conditionals;
		boolean constrainedFlag$sample47 = true;
		boolean constrainedFlag$sample52 = true;
		boolean constrainedFlag$sample55 = true;
		boolean constrainedFlag$sample57 = true;
		boolean constrainedFlag$sample62 = true;
		boolean constrainedFlag$sample67 = true;
		boolean constrainedFlag$sample72 = true;
		int evidence;
		boolean fixedFlag$sample47 = false;
		boolean fixedFlag$sample52 = false;
		boolean fixedFlag$sample55 = false;
		boolean fixedFlag$sample57 = false;
		boolean fixedFlag$sample60 = false;
		boolean fixedFlag$sample62 = false;
		boolean fixedFlag$sample636 = false;
		boolean fixedFlag$sample65 = false;
		boolean fixedFlag$sample67 = false;
		boolean fixedFlag$sample70 = false;
		boolean fixedFlag$sample72 = false;
		boolean fixedFlag$sample75 = false;
		boolean fixedProbFlag$sample47 = false;
		boolean fixedProbFlag$sample50 = false;
		boolean fixedProbFlag$sample52 = false;
		boolean fixedProbFlag$sample55 = false;
		boolean fixedProbFlag$sample57 = false;
		boolean fixedProbFlag$sample60 = false;
		boolean fixedProbFlag$sample62 = false;
		boolean fixedProbFlag$sample636 = false;
		boolean fixedProbFlag$sample65 = false;
		boolean fixedProbFlag$sample67 = false;
		boolean fixedProbFlag$sample70 = false;
		boolean fixedProbFlag$sample72 = false;
		boolean fixedProbFlag$sample75 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$c1;
		double logProbability$c10;
		double logProbability$c11;
		double logProbability$c12;
		double logProbability$c2;
		double logProbability$c3;
		double logProbability$c4;
		double logProbability$c5;
		double logProbability$c6;
		double logProbability$c7;
		double logProbability$c8;
		double logProbability$c9;
		double logProbability$terminalVariable;
		double[] priors;
		boolean system$gibbsForward = true;
		int terminalVariable;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
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
		}

		// Getter for a.
		final double[][][][][] get$a() {
			return a;
		}

		// Getter for c1.
		final int get$c1() {
			return c1;
		}

		// Setter for c1.
		final void set$c1(int cv$value, boolean allocated$) {
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
		final int get$c10() {
			return c10;
		}

		// Setter for c10.
		final void set$c10(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c10 including if probabilities need to be
			// updated.
			c10 = cv$value;
			
			// Unset the fixed probability flag for sample 70 as it depends on c10.
			fixedProbFlag$sample70 = false;
		}

		// Getter for c11.
		final int get$c11() {
			return c11;
		}

		// Setter for c11.
		final void set$c11(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c11 including if probabilities need to be
			// updated.
			c11 = cv$value;
			
			// Unset the fixed probability flag for sample 72 as it depends on c11.
			fixedProbFlag$sample72 = false;
			
			// Unset the fixed probability flag for sample 75 as it depends on c11.
			fixedProbFlag$sample75 = false;
		}

		// Getter for c12.
		final int get$c12() {
			return c12;
		}

		// Setter for c12.
		final void set$c12(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c12 including if probabilities need to be
			// updated.
			c12 = cv$value;
			
			// Unset the fixed probability flag for sample 75 as it depends on c12.
			fixedProbFlag$sample75 = false;
		}

		// Getter for c2.
		final int get$c2() {
			return c2;
		}

		// Getter for c3.
		final int get$c3() {
			return c3;
		}

		// Setter for c3.
		final void set$c3(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c3 including if probabilities need to be
			// updated.
			c3 = cv$value;
			
			// Unset the fixed probability flag for sample 52 as it depends on c3.
			fixedProbFlag$sample52 = false;
			
			// Unset the fixed probability flag for sample 55 as it depends on c3.
			fixedProbFlag$sample55 = false;
		}

		// Getter for c4.
		final int get$c4() {
			return c4;
		}

		// Setter for c4.
		final void set$c4(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c4 including if probabilities need to be
			// updated.
			c4 = cv$value;
			
			// Unset the fixed probability flag for sample 55 as it depends on c4.
			fixedProbFlag$sample55 = false;
			
			// Unset the fixed probability flag for sample 636 as it depends on c4.
			fixedProbFlag$sample636 = false;
		}

		// Getter for c5.
		final int get$c5() {
			return c5;
		}

		// Setter for c5.
		final void set$c5(int cv$value, boolean allocated$) {
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
		final int get$c6() {
			return c6;
		}

		// Setter for c6.
		final void set$c6(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c6 including if probabilities need to be
			// updated.
			c6 = cv$value;
			
			// Unset the fixed probability flag for sample 60 as it depends on c6.
			fixedProbFlag$sample60 = false;
		}

		// Getter for c7.
		final int get$c7() {
			return c7;
		}

		// Setter for c7.
		final void set$c7(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c7 including if probabilities need to be
			// updated.
			c7 = cv$value;
			
			// Unset the fixed probability flag for sample 62 as it depends on c7.
			fixedProbFlag$sample62 = false;
			
			// Unset the fixed probability flag for sample 65 as it depends on c7.
			fixedProbFlag$sample65 = false;
		}

		// Getter for c8.
		final int get$c8() {
			return c8;
		}

		// Setter for c8.
		final void set$c8(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of c8 including if probabilities need to be
			// updated.
			c8 = cv$value;
			
			// Unset the fixed probability flag for sample 65 as it depends on c8.
			fixedProbFlag$sample65 = false;
		}

		// Getter for c9.
		final int get$c9() {
			return c9;
		}

		// Setter for c9.
		final void set$c9(int cv$value, boolean allocated$) {
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
		final double[][] get$conditionals() {
			return conditionals;
		}

		// Getter for evidence.
		final int get$evidence() {
			return evidence;
		}

		// Setter for evidence.
		final void set$evidence(int cv$value, boolean allocated$) {
			evidence = cv$value;
		}

		// Getter for fixedFlag$sample47.
		final boolean get$fixedFlag$sample47() {
			return fixedFlag$sample47;
		}

		// Setter for fixedFlag$sample47.
		final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
			// need to be updated.
			fixedFlag$sample47 = cv$value;
			
			// Substituted "fixedFlag$sample47" with its value "cv$value".
			constrainedFlag$sample47 = (cv$value || constrainedFlag$sample47);
			
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
		final boolean get$fixedFlag$sample52() {
			return fixedFlag$sample52;
		}

		// Setter for fixedFlag$sample52.
		final void set$fixedFlag$sample52(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
			// need to be updated.
			fixedFlag$sample52 = cv$value;
			
			// Substituted "fixedFlag$sample52" with its value "cv$value".
			constrainedFlag$sample52 = (cv$value || constrainedFlag$sample52);
			
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
		final boolean get$fixedFlag$sample55() {
			return fixedFlag$sample55;
		}

		// Setter for fixedFlag$sample55.
		final void set$fixedFlag$sample55(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
			// need to be updated.
			fixedFlag$sample55 = cv$value;
			
			// Substituted "fixedFlag$sample55" with its value "cv$value".
			constrainedFlag$sample55 = (cv$value || constrainedFlag$sample55);
			
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
		final boolean get$fixedFlag$sample57() {
			return fixedFlag$sample57;
		}

		// Setter for fixedFlag$sample57.
		final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
			// need to be updated.
			fixedFlag$sample57 = cv$value;
			
			// Substituted "fixedFlag$sample57" with its value "cv$value".
			constrainedFlag$sample57 = (cv$value || constrainedFlag$sample57);
			
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
		final boolean get$fixedFlag$sample60() {
			return fixedFlag$sample60;
		}

		// Setter for fixedFlag$sample60.
		final void set$fixedFlag$sample60(boolean cv$value, boolean allocated$) {
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
		final boolean get$fixedFlag$sample62() {
			return fixedFlag$sample62;
		}

		// Setter for fixedFlag$sample62.
		final void set$fixedFlag$sample62(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample62 including if probabilities
			// need to be updated.
			fixedFlag$sample62 = cv$value;
			
			// Substituted "fixedFlag$sample62" with its value "cv$value".
			constrainedFlag$sample62 = (cv$value || constrainedFlag$sample62);
			
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
		final boolean get$fixedFlag$sample636() {
			return fixedFlag$sample636;
		}

		// Setter for fixedFlag$sample636.
		final void set$fixedFlag$sample636(boolean cv$value, boolean allocated$) {
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
		final boolean get$fixedFlag$sample65() {
			return fixedFlag$sample65;
		}

		// Setter for fixedFlag$sample65.
		final void set$fixedFlag$sample65(boolean cv$value, boolean allocated$) {
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
		final boolean get$fixedFlag$sample67() {
			return fixedFlag$sample67;
		}

		// Setter for fixedFlag$sample67.
		final void set$fixedFlag$sample67(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample67 including if probabilities
			// need to be updated.
			fixedFlag$sample67 = cv$value;
			
			// Substituted "fixedFlag$sample67" with its value "cv$value".
			constrainedFlag$sample67 = (cv$value || constrainedFlag$sample67);
			
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
		final boolean get$fixedFlag$sample70() {
			return fixedFlag$sample70;
		}

		// Setter for fixedFlag$sample70.
		final void set$fixedFlag$sample70(boolean cv$value, boolean allocated$) {
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
		final boolean get$fixedFlag$sample72() {
			return fixedFlag$sample72;
		}

		// Setter for fixedFlag$sample72.
		final void set$fixedFlag$sample72(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample72 including if probabilities
			// need to be updated.
			fixedFlag$sample72 = cv$value;
			
			// Substituted "fixedFlag$sample72" with its value "cv$value".
			constrainedFlag$sample72 = (cv$value || constrainedFlag$sample72);
			
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
		final boolean get$fixedFlag$sample75() {
			return fixedFlag$sample75;
		}

		// Setter for fixedFlag$sample75.
		final void set$fixedFlag$sample75(boolean cv$value, boolean allocated$) {
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
		final double get$logProbability$c1() {
			return logProbability$c1;
		}

		// Getter for logProbability$c10.
		final double get$logProbability$c10() {
			return logProbability$c10;
		}

		// Getter for logProbability$c11.
		final double get$logProbability$c11() {
			return logProbability$c11;
		}

		// Getter for logProbability$c12.
		final double get$logProbability$c12() {
			return logProbability$c12;
		}

		// Getter for logProbability$c2.
		final double get$logProbability$c2() {
			return logProbability$c2;
		}

		// Getter for logProbability$c3.
		final double get$logProbability$c3() {
			return logProbability$c3;
		}

		// Getter for logProbability$c4.
		final double get$logProbability$c4() {
			return logProbability$c4;
		}

		// Getter for logProbability$c5.
		final double get$logProbability$c5() {
			return logProbability$c5;
		}

		// Getter for logProbability$c6.
		final double get$logProbability$c6() {
			return logProbability$c6;
		}

		// Getter for logProbability$c7.
		final double get$logProbability$c7() {
			return logProbability$c7;
		}

		// Getter for logProbability$c8.
		final double get$logProbability$c8() {
			return logProbability$c8;
		}

		// Getter for logProbability$c9.
		final double get$logProbability$c9() {
			return logProbability$c9;
		}

		// Getter for logProbability$terminalVariable.
		final double get$logProbability$terminalVariable() {
			return logProbability$terminalVariable;
		}

		// Getter for priors.
		final double[] get$priors() {
			return priors;
		}

		// Getter for terminalVariable.
		final int get$terminalVariable() {
			return terminalVariable;
		}

		// Setter for terminalVariable.
		final void set$terminalVariable(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of terminalVariable including if probabilities
			// need to be updated.
			terminalVariable = cv$value;
			
			// Unset the fixed probability flag for sample 636 as it depends on terminalVariable.
			fixedProbFlag$sample636 = false;
		}
	}

    private final ComputedIntegerInternal $c1 = new ComputedIntegerInternal(this, "c1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c1(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample47(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample47())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c1 of type int from the Sandwood model. */
    public final ComputedInteger c1 = $c1;

    private final ComputedIntegerInternal $c10 = new ComputedIntegerInternal(this, "c10", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c10(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c10(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c10(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample70(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample70())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c10 of type int from the Sandwood model. */
    public final ComputedInteger c10 = $c10;

    private final ComputedIntegerInternal $c11 = new ComputedIntegerInternal(this, "c11", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c11(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c11(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c11(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample72(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample72())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c11 of type int from the Sandwood model. */
    public final ComputedInteger c11 = $c11;

    private final ComputedIntegerInternal $c12 = new ComputedIntegerInternal(this, "c12", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c12(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c12(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c12(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample75(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample75())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c12 of type int from the Sandwood model. */
    public final ComputedInteger c12 = $c12;

    private final ComputedIntegerInternal $c2 = new ComputedIntegerInternal(this, "c2", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c2(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable c2 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing c2 of type int from the Sandwood model. */
    public final ComputedInteger c2 = $c2;

    private final ComputedIntegerInternal $c3 = new ComputedIntegerInternal(this, "c3", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c3(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c3(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c3(); }

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

	/** Computed variable representing c3 of type int from the Sandwood model. */
    public final ComputedInteger c3 = $c3;

    private final ComputedIntegerInternal $c4 = new ComputedIntegerInternal(this, "c4", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c4(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c4(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c4(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample55(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample55())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c4 of type int from the Sandwood model. */
    public final ComputedInteger c4 = $c4;

    private final ComputedIntegerInternal $c5 = new ComputedIntegerInternal(this, "c5", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c5(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c5(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c5(); }

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

	/** Computed variable representing c5 of type int from the Sandwood model. */
    public final ComputedInteger c5 = $c5;

    private final ComputedIntegerInternal $c6 = new ComputedIntegerInternal(this, "c6", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c6(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c6(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c6(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample60(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample60())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c6 of type int from the Sandwood model. */
    public final ComputedInteger c6 = $c6;

    private final ComputedIntegerInternal $c7 = new ComputedIntegerInternal(this, "c7", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c7(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c7(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c7(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample62(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample62())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c7 of type int from the Sandwood model. */
    public final ComputedInteger c7 = $c7;

    private final ComputedIntegerInternal $c8 = new ComputedIntegerInternal(this, "c8", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c8(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c8(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c8(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample65(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample65())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c8 of type int from the Sandwood model. */
    public final ComputedInteger c8 = $c8;

    private final ComputedIntegerInternal $c9 = new ComputedIntegerInternal(this, "c9", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$c9(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$c9(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$c9(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample67(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample67())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing c9 of type int from the Sandwood model. */
    public final ComputedInteger c9 = $c9;

    private final ComputedIntegerInternal $terminalVariable = new ComputedIntegerInternal(this, "terminalVariable", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$terminalVariable(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$terminalVariable(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$terminalVariable(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample636(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample636())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing terminalVariable of type int from the Sandwood model.
	 */
    public final ComputedInteger terminalVariable = $terminalVariable;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerInternal $evidence = new ObservedIntegerInternal(this, "evidence") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$evidence();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$evidence(value, allocated); }
    };

	/** Observed variable representing evidence of type int from the Sandwood model. */
    public final ObservedInteger evidence = $evidence;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$c1, $c10, $c11, $c12, $c2, $c3, $c4, $c5, $c6, $c7, $c8, $c9, $terminalVariable};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public TerminalVariables() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("c1", $c1);
        $computedVariables.put("c10", $c10);
        $computedVariables.put("c11", $c11);
        $computedVariables.put("c12", $c12);
        $computedVariables.put("c2", $c2);
        $computedVariables.put("c3", $c3);
        $computedVariables.put("c4", $c4);
        $computedVariables.put("c5", $c5);
        $computedVariables.put("c6", $c6);
        $computedVariables.put("c7", $c7);
        $computedVariables.put("c8", $c8);
        $computedVariables.put("c9", $c9);
        $computedVariables.put("terminalVariable", $terminalVariable);

        //Observed scalar fields
        $regularObservedValues.put("evidence", $evidence);

        TerminalVariables$SingleThreadCPU core = new TerminalVariables$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param evidence The value to set evidence to.
	 */
    public TerminalVariables(int evidence) {
        this();
        this.evidence.setValue(evidence);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new TerminalVariables$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new TerminalVariables$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input evidence */
        public final int evidence;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param evidence The value to set evidence to.
		 */
        public AllInputs(int evidence) {
            this.evidence = evidence;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of c1 after a convention execution step. */
        public final int c1;
		/** Field holding the value of c10 after a convention execution step. */
        public final int c10;
		/** Field holding the value of c11 after a convention execution step. */
        public final int c11;
		/** Field holding the value of c12 after a convention execution step. */
        public final int c12;
		/** Field holding the value of c2 after a convention execution step. */
        public final int c2;
		/** Field holding the value of c3 after a convention execution step. */
        public final int c3;
		/** Field holding the value of c4 after a convention execution step. */
        public final int c4;
		/** Field holding the value of c5 after a convention execution step. */
        public final int c5;
		/** Field holding the value of c6 after a convention execution step. */
        public final int c6;
		/** Field holding the value of c7 after a convention execution step. */
        public final int c7;
		/** Field holding the value of c8 after a convention execution step. */
        public final int c8;
		/** Field holding the value of c9 after a convention execution step. */
        public final int c9;
		/** Field holding the value of terminalVariable after a convention execution step. */
        public final int terminalVariable;

        InferredValueOutputs(TerminalVariables system$model) {
            this.c1 = system$model.c1.getSamples()[0];
            this.c10 = system$model.c10.getSamples()[0];
            this.c11 = system$model.c11.getSamples()[0];
            this.c12 = system$model.c12.getSamples()[0];
            this.c2 = system$model.c2.getSamples()[0];
            this.c3 = system$model.c3.getSamples()[0];
            this.c4 = system$model.c4.getSamples()[0];
            this.c5 = system$model.c5.getSamples()[0];
            this.c6 = system$model.c6.getSamples()[0];
            this.c7 = system$model.c7.getSamples()[0];
            this.c8 = system$model.c8.getSamples()[0];
            this.c9 = system$model.c9.getSamples()[0];
            this.terminalVariable = system$model.terminalVariable.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable c1 */
        public final double c1;
		/** Field holding the log probability of computed variable c10 */
        public final double c10;
		/** Field holding the log probability of computed variable c11 */
        public final double c11;
		/** Field holding the log probability of computed variable c12 */
        public final double c12;
		/** Field holding the log probability of computed variable c2 */
        public final double c2;
		/** Field holding the log probability of computed variable c3 */
        public final double c3;
		/** Field holding the log probability of computed variable c4 */
        public final double c4;
		/** Field holding the log probability of computed variable c5 */
        public final double c5;
		/** Field holding the log probability of computed variable c6 */
        public final double c6;
		/** Field holding the log probability of computed variable c7 */
        public final double c7;
		/** Field holding the log probability of computed variable c8 */
        public final double c8;
		/** Field holding the log probability of computed variable c9 */
        public final double c9;
		/** Field holding the log probability of computed variable terminalVariable */
        public final double terminalVariable;

        LogProbabilities(TerminalVariables system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.c1 = system$model.c1.getLogProbability();
            this.c10 = system$model.c10.getLogProbability();
            this.c11 = system$model.c11.getLogProbability();
            this.c12 = system$model.c12.getLogProbability();
            this.c2 = system$model.c2.getLogProbability();
            this.c3 = system$model.c3.getLogProbability();
            this.c4 = system$model.c4.getLogProbability();
            this.c5 = system$model.c5.getLogProbability();
            this.c6 = system$model.c6.getLogProbability();
            this.c7 = system$model.c7.getLogProbability();
            this.c8 = system$model.c8.getLogProbability();
            this.c9 = system$model.c9.getLogProbability();
            this.terminalVariable = system$model.terminalVariable.getLogProbability();
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
		/** Field holding the probability of computed variable c1 */
        public final double c1;
		/** Field holding the probability of computed variable c10 */
        public final double c10;
		/** Field holding the probability of computed variable c11 */
        public final double c11;
		/** Field holding the probability of computed variable c12 */
        public final double c12;
		/** Field holding the probability of computed variable c2 */
        public final double c2;
		/** Field holding the probability of computed variable c3 */
        public final double c3;
		/** Field holding the probability of computed variable c4 */
        public final double c4;
		/** Field holding the probability of computed variable c5 */
        public final double c5;
		/** Field holding the probability of computed variable c6 */
        public final double c6;
		/** Field holding the probability of computed variable c7 */
        public final double c7;
		/** Field holding the probability of computed variable c8 */
        public final double c8;
		/** Field holding the probability of computed variable c9 */
        public final double c9;
		/** Field holding the probability of computed variable terminalVariable */
        public final double terminalVariable;

        Probabilities(TerminalVariables system$model) {
            this.$modelProbability = system$model.getProbability();
            this.c1 = system$model.c1.getProbability();
            this.c10 = system$model.c10.getProbability();
            this.c11 = system$model.c11.getProbability();
            this.c12 = system$model.c12.getProbability();
            this.c2 = system$model.c2.getProbability();
            this.c3 = system$model.c3.getProbability();
            this.c4 = system$model.c4.getProbability();
            this.c5 = system$model.c5.getProbability();
            this.c6 = system$model.c6.getProbability();
            this.c7 = system$model.c7.getProbability();
            this.c8 = system$model.c8.getProbability();
            this.c9 = system$model.c9.getProbability();
            this.terminalVariable = system$model.terminalVariable.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of c1 after an infer model call. */
        public final int[] c1;
		/** Field holding the MAP or Sample value of c10 after an infer model call. */
        public final int[] c10;
		/** Field holding the MAP or Sample value of c11 after an infer model call. */
        public final int[] c11;
		/** Field holding the MAP or Sample value of c12 after an infer model call. */
        public final int[] c12;
		/** Field holding the MAP or Sample value of c3 after an infer model call. */
        public final int[] c3;
		/** Field holding the MAP or Sample value of c4 after an infer model call. */
        public final int[] c4;
		/** Field holding the MAP or Sample value of c5 after an infer model call. */
        public final int[] c5;
		/** Field holding the MAP or Sample value of c6 after an infer model call. */
        public final int[] c6;
		/** Field holding the MAP or Sample value of c7 after an infer model call. */
        public final int[] c7;
		/** Field holding the MAP or Sample value of c8 after an infer model call. */
        public final int[] c8;
		/** Field holding the MAP or Sample value of c9 after an infer model call. */
        public final int[] c9;
		/**
		 * Field holding the MAP or Sample value of terminalVariable after an infer model
		 * call.
		 */
        public final int[] terminalVariable;

        InferredModelOutputs(TerminalVariables system$model) {
            this.c1 = system$model.getInferredValue(system$model.$c1);
            this.c10 = system$model.getInferredValue(system$model.$c10);
            this.c11 = system$model.getInferredValue(system$model.$c11);
            this.c12 = system$model.getInferredValue(system$model.$c12);
            this.c3 = system$model.getInferredValue(system$model.$c3);
            this.c4 = system$model.getInferredValue(system$model.$c4);
            this.c5 = system$model.getInferredValue(system$model.$c5);
            this.c6 = system$model.getInferredValue(system$model.$c6);
            this.c7 = system$model.getInferredValue(system$model.$c7);
            this.c8 = system$model.getInferredValue(system$model.$c8);
            this.c9 = system$model.getInferredValue(system$model.$c9);
            this.terminalVariable = system$model.getInferredValue(system$model.$terminalVariable);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}