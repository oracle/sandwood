package org.sandwood.compiler.tests.parser;

interface LDATest$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for alpha.
	public double[] get$alpha();

	// Getter for beta.
	public double[] get$beta();

	// Getter for documents.
	public int[][] get$documents();

	// Setter for documents.
	public void set$documents(int[][] cv$value);

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample59.
	public boolean get$fixedFlag$sample59();

	// Setter for fixedFlag$sample59.
	public void set$fixedFlag$sample59(boolean cv$value);

	// Getter for fixedFlag$sample62.
	public boolean get$fixedFlag$sample62();

	// Setter for fixedFlag$sample62.
	public void set$fixedFlag$sample62(boolean cv$value);

	// Getter for length$documents.
	public int[] get$length$documents();

	// Setter for length$documents.
	public void set$length$documents(int[] cv$value);

	// Getter for logProbability$phi.
	public double get$logProbability$phi();

	// Getter for logProbability$theta.
	public double get$logProbability$theta();

	// Getter for logProbability$w.
	public double get$logProbability$w();

	// Getter for logProbability$z.
	public double get$logProbability$z();

	// Getter for noTopics.
	public int get$noTopics();

	// Setter for noTopics.
	public void set$noTopics(int cv$value);

	// Getter for phi.
	public double[][] get$phi();

	// Setter for phi.
	public void set$phi(double[][] cv$value);

	// Getter for theta.
	public double[][] get$theta();

	// Setter for theta.
	public void set$theta(double[][] cv$value);

	// Getter for vocabSize.
	public int get$vocabSize();

	// Setter for vocabSize.
	public void set$vocabSize(int cv$value);

	// Getter for w.
	public int[][] get$w();

	// Setter for w.
	public void set$w(int[][] cv$value);

	// Getter for z.
	public int[][] get$z();

	// Setter for z.
	public void set$z(int[][] cv$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}