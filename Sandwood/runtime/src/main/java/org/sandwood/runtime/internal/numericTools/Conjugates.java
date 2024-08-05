/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.numericTools;

import org.sandwood.random.internal.Rng;

/**
 * A class holding static methods for calculating the values of conjugate pairs during inference.
 *
 * @author djgoodma
 *
 */
public class Conjugates {

    /**
     * A private constructor as this class should never be instantiated.
     */
    private Conjugates() {}

    /**
     * A method to calculate the inferred output of a Beta distribution when paired with Bernoulli and Binomial
     * distribution.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param alpha The parameter alpha provided to the Beta distribution.
     * @param beta  The parameter beta provided to the Beta distribution.
     * @param sum   The number of true values sampled from the Bernoulli and Binomial distributions.
     * @param n     The total number of samples from the Bernoulli and Binomial distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static double sampleConjugateBetaBinomial(Rng rng, double alpha, double beta, double sum, double n) {
        alpha += sum;
        beta += n - sum;
        double x = DistributionSampling.sampleGamma(rng, alpha);
        double y = DistributionSampling.sampleGamma(rng, beta);
        return x / (x + y);
    }

    /**
     * A method to calculate the inferred output of a Dirichlet distribution when paired with Categorical or Multinomial
     * distributions.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param hyper The hyper-parameter provided to the Dirichlet distribution.
     * @param count A count of the number of times each possible output was sampled from the Categorical distributions.
     * @param p     An array that will be populated with a value sampled from the distribution described by the inputs.
     */
    public static void sampleConjugateDirichletCategorical(Rng rng, double[] hyper, double[] count, double[] p) {
        double sum = 0.0;
        int N = p.length;
        for(int i = 0; i < N; i++) {
            p[i] = DistributionSampling.sampleGamma(rng, hyper[i] + count[i]);
            sum += p[i];
        }

        for(int i = 0; i < N; i++) {
            p[i] /= sum;
        }
    }

    /*
     * public static final void sampleUpConjugateDirichletT(Rng Rng, int c, double[] hyper, int[][] count, double[][] p)
     * { for (int i = 0; i < p.length; i++) { p[i][c] = DistributionSampling.sampleGamma(Rng, hyper[c] + count[i][c]); }
     * }
     */

    /**
     * A method to calculate the inferred output of a Gamma distribution when paired with the lambda of Exponential
     * distributions.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param alpha The parameter alpha provided to the Gamma distribution.
     * @param beta  The parameter beta provided to the Gamms distribution.
     * @param sum   The sum of the samples from Exponential distributions.
     * @param n     The number of samples from the Exponential distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static double sampleConjugateGammaExponential(Rng rng, double alpha, double beta, double sum, int n) {
        return DistributionSampling.sampleGamma(rng, alpha + n, beta + sum);
    }

    /**
     * A method to calculate the inferred output of a Gamma distribution when paired with the variance of Gaussian
     * distributions.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param alpha The parameter alpha provided to the Gamma distribution.
     * @param beta  The parameter beta provided to the Gamma distribution.
     * @param sum   The sum of the samples from Gaussian distributions.
     * @param n     The number of samples from the Gaussian distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static final double sampleConjugateGammaGaussian(Rng rng, double alpha, double beta, double sum, int n) {
        alpha += ((double) n) / 2;
        beta += sum / 2;
        return DistributionSampling.sampleGamma(rng, alpha, beta);
    }

    /**
     * A method to calculate the inferred output of a Gamma distribution when paired with Poisson distributions.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param alpha The parameter alpha provided to the Gamma distribution.
     * @param beta  The parameter beta provided to the Gamma distribution.
     * @param sum   The sum of the samples from Poisson distributions.
     * @param n     The number of samples from the Poisson distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static double sampleConjugateGammaPoisson(Rng rng, double alpha, double beta, double sum, double n) {
        alpha += sum;
        beta += n;
        return DistributionSampling.sampleGamma(rng, alpha, beta);
    }

    /**
     * A method to calculate the inferred output of a Gaussian distribution when paired with Gaussian distributions with
     * known variance.
     * 
     * @param rng         A random number generator used in the sampling.
     * @param mu0         The mean provided to the prior Gaussian.
     * @param variance0   The variance provided to the prior Gaussian.
     * @param variance    The variance provided to the consuming Gaussian distributions.
     * @param sum         The sum of the values sampled from the consuming Gaussian distributions.
     * @param denominator The number of samples from the consuming Gaussian distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static double sampleConjugateGaussianGaussian(Rng rng, double mu0, double variance0, double variance,
            double sum, double denominator) {
        double variance0Inv = 1.0 / variance0;
        double varianceInv = 1.0 / variance;
        double postSigma = 1.0 / (variance0Inv + denominator * varianceInv);
        double postMean = (mu0 * variance0Inv + sum * varianceInv) * postSigma;
        return DistributionSampling.sampleGaussian(rng, postMean, postSigma);
    }

    /**
     * A method to calculate the inferred output of an Inverse Gamma distribution when paired with the variance of
     * Gaussian distributions.
     * 
     * @param rng   A random number generator used in the sampling.
     * @param alpha The parameter alpha provided to the InverseGamma distribution.
     * @param beta  The parameter beta provided to the InverseGamma distribution.
     * @param sum   The sum of the samples from Gaussian distributions.
     * @param n     The number of samples from the Gaussian distributions.
     * @return A value randomly sampled from the distribution described by the inputs.
     */
    public static double sampleConjugateInverseGammaGaussian(Rng rng, double alpha, double beta, double sum, int n) {
        alpha += ((double) n) / 2;
        beta += sum / 2;
        return DistributionSampling.sampleInverseGamma(rng, alpha, beta);
    }
}
