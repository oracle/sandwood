/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.numericTools;

import static java.lang.Math.E;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.floor;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Arrays;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.random.internal.Rng;

/**
 * Class of numeric methods. TODO Review proposal and conjugate methods.
 */

public class DistributionSampling {

    /**
     * Private constructor to ensure that this class is never instantiated.
     */
    private DistributionSampling() {}

    // Bernoulli
    /**
     * Method to sample a boolean from a Bernoulli distribution.
     * 
     * @param Rng     A random number generator for the sampling.
     * @param success The probability of the Bernoulli producing a true value.
     * @return A boolean value sampled from the describe Bernoulli distribution.
     */
    public static boolean sampleBernoulli(Rng Rng, double success) {
        return Rng.uniform() <= success;
    }

    /**
     * calculate the probability of sampling x from a Bernoulli
     *
     * @param x       The value to calculate the probability for.
     * @param success The probability of the Bernoulli value generating true.
     * @return The probability of the Bernoulli generating the value x.
     */
    public static double probabilityBernoulli(boolean x, double success) {
        if(x)
            return success;
        else
            return (1 - success);
    }

    /**
     * calculate the log probability of sampling x from a Bernoulli
     *
     * @param x       The value to calculate the probability for.
     * @param success The probability of the Bernoulli value generating true.
     * @return The log probability of the Bernoulli generating the value x.
     */
    public static double logProbabilityBernoulli(boolean x, double success) {
        if(x)
            return log(success);
        else
            return log(1 - success);
    }

    /**
     * Method to add the Bernoulli distribution described by to an array holding the cumulative distributions so far.
     * 
     * @param distribution The array holding the existing cumulative probabilities.
     * @param scale        A positive scale value that the added distribution should be scaled by.
     * @param p            The probability of the Bernoulli distribution generating true.
     */
    public static void addProbabilityDistributionBernoulli(double[] distribution, double scale, double p) {
        distribution[0] += scale * (1 - p);
        distribution[1] += scale * p;
    }

    // Beta
    /**
     * Method to sample a value from a Beta distribution. <a href="https://en.wikipedia.org/wiki/Beta_distribution">
     * https://en.wikipedia.org/wiki/Beta_distribution</a>
     *
     * @param Rng   A random number generator for the sampling.
     * @param alpha The alpha parameter to the Beta distribution.
     * @param beta  The beta parameter to the Beta distribution.
     * @return A value randomly sampled from the Beta distribution.
     */
    public static double sampleBeta(Rng Rng, double alpha, double beta) {
        double x = sampleGamma(Rng, alpha);
        double y = sampleGamma(Rng, beta);

        return x / (x + y);
    }

    /**
     * Calculate the probability of sampling x from a Beta distribution.
     *
     * @param x     The value to calculate the probability of distribution for.
     * @param alpha The alpha parameter to the Beta distribution.
     * @param beta  The beta parameter to the Beta distribution.
     * @return The probability of the specified Beta distribution producing the value x.
     */
    public static double probabilityBeta(double x, double alpha, double beta) {
        if(x < 0 || x > 1)
            return 0;
        // Constant to ensure that the value over the range [0..1] integrates to 1.
        double constant = Gamma.gamma(alpha + beta) / (Gamma.gamma(alpha) * Gamma.gamma(beta));
        return constant * pow(x, alpha - 1) * pow(1 - x, beta - 1);
    }

    /**
     * Calculate the log probability of sampling x from a Beta distribution.
     *
     * @param x     The value to calculate the probability of distribution for.
     * @param alpha The alpha parameter to the Beta distribution.
     * @param beta  The beta parameter to the Beta distribution.
     * @return The probability of the specified Beta distribution producing the value x.
     */
    public static double logProbabilityBeta(double x, double alpha, double beta) {
        if(x < 0 || x > 1)
            return Double.NEGATIVE_INFINITY;
        // Constant to ensure that the value over the range [0..1] integrates to 1.
        double constant = Gamma.logGamma(alpha + beta) - (Gamma.logGamma(alpha) + Gamma.logGamma(beta));
        return constant + (alpha - 1) * log(x) + (beta - 1) * log(1 - x);
    }

    // Binomial
    /**
     * Method to sample a value from a binomial distribution.
     * 
     * @param Rng      A random number generator for the sampling.
     * @param probTrue The probability of each test in the distribution generating a positive value.
     * @param numTests The total number of tests represented by this distribution.
     * @return The number of tests that returned true in this sampling.
     */
    public static int sampleBinomial(Rng Rng, double probTrue, int numTests) {
        int sum = 0;
        for(int i = 0; i < numTests; i++)
            sum += Rng.uniform() <= probTrue ? 1 : 0;
        return sum;
    }

    /**
     * Calculate the probability of x tests returning true from a Binomial distribution.
     *
     * @param numTrue  Number of tests that returned true.
     * @param probTrue The probability of each test returning true.
     * @param numTests Total number of tests in the distribution.
     * @return The probability of the described distribution returning numTrue positive tests.
     */
    public static double probabilityBinomial(int numTrue, double probTrue, int numTests) {
        double result = logProbabilityBinomial(numTrue, probTrue, numTests);
        return Math.exp(result);
    }

    /**
     * Calculate the log probability of x tests returning true from a Binomial distribution.
     *
     * @param numTrue  Number of tests that returned true.
     * @param probTrue The probability of each test returning true.
     * @param numTests Total number of tests in the distribution.
     * @return The log probability of the described distribution returning numTrue positive tests.
     */
    public static double logProbabilityBinomial(int numTrue, double probTrue, int numTests) {
        // Catch the edge cases as -Infinity times integer 0 seems to generate NaN. I
        // suspect this is because the value is turned into a floating point first.
        // Floating point 0 also represents numbers that are too small to be anything
        // else, and this is why IEEE returns NaN, not 0.
        if(probTrue == 1)
            return (numTrue == numTests) ? 0.0 : Double.NEGATIVE_INFINITY;
        if(probTrue == 0)
            return (numTrue == 0) ? 0.0 : Double.NEGATIVE_INFINITY;
        if(numTests < numTrue)
            return Double.NEGATIVE_INFINITY;

        // Log probability of failure
        double fail = log(1.0 - probTrue);
        probTrue = log(probTrue);

        double p = fail * (numTests - numTrue) + probTrue * numTrue;

        // factorial and cancel for this.
        double coefficient = ApproximateFactorial.approxLogFac(numTests) - ApproximateFactorial.approxLogFac(numTrue)
                - ApproximateFactorial.approxLogFac(numTests - numTrue);

        // put it all together
        return p + coefficient;
    }

    /**
     * Method to add the Binomial distribution described by to an array holding the cumulative distributions so far.
     * 
     * @param distribution The array holding the existing cumulative probabilities.
     * @param scale        A positive scale value that the added distribution should be scaled by.
     * @param probTrue     The probability of each test returning true.
     * @param numTests     Total number of tests in the distribution.
     */
    public static void addProbabilityDistributionBinomial(double[] distribution, double scale, double probTrue,
            int numTests) {
        // Catch the edge cases as -Infinity times integer 0 seems to generate NaN. I
        // suspect this is because the value is turned into a floating point first.
        // Floating point 0 also represents numbers that are too small to be anything
        // else, and this is why IEEE returns NaN, not 0.
        if(probTrue == 1)
            distribution[numTests] += scale;
        else if(probTrue == 0)
            distribution[0] += scale;
        else {
            // setup constants
            double fail = log(1.0 - probTrue);
            double success = log(probTrue);
            double difference = success - fail;

            // Initial probability with all the values as failures;
            double sampleProb = fail * numTests;
            // Initial coefficient: log(1.0);
            double coefficient = 0.0;

            // Calculate intersection
            double prob = exp(sampleProb);
            distribution[0] += scale * prob;

            for(int positive = 1; positive <= numTests; positive++) {
                // Update values
                sampleProb += difference;
                // Update the coefficient TODO, as we are likely to be using small
                // values here, do we just call the approximate factorial method to
                // take advantage of the pre-computed values?
                coefficient = log((numTests + 1) - positive) - log(positive);

                // Calculate intersection
                prob = exp(sampleProb + coefficient);
                distribution[positive] += scale * prob;
            }
        }
    }

    // Categorical
    /**
     * Method to sample a value from a Categorical distribution.
     * 
     * @param rng          A random number generator for the sampling.
     * @param elementProbs The probability of returning each possible value from the categorical distribution.
     * @return The sampled value.
     */
    public static int sampleCategorical(Rng rng, double[] elementProbs) {
        // Scale a uniform value to pick a category.
        double val = rng.uniform();

        // Loop through to find the category this value matches with.
        int l = elementProbs.length;
        double sum = 0;
        for(int i = 0; i < l; i++) {
            sum += elementProbs[i];
            if(sum >= val)
                return i;
        }
        throw new SandwoodException("This should be unreachable");
    }

    /**
     * Calculate the probability of category x for a list of categories each with a probability
     * <p>
     * TODO this currently assumes that the arrays sum to 1. Either add code to protect against this here or remove the
     * code handling it in the sample code as it is wasted.
     *
     * @param x            The value to generate the probability for.
     * @param elementProbs The probability of each category.
     * @return The probability of category x being produced by this distribution.
     */
    public static double probabilityCategorical(int x, double[] elementProbs) {
        if(x < 0 || x >= elementProbs.length)
            return 0;
        else
            return elementProbs[x];
    }

    /**
     * Calculate the probability of category x for a list of categories each with a probability
     * <p>
     * TODO this currently assumes that the arrays sum to 1. Either add code to protect against this here or remove the
     * code handling it in the sample code as it is wasted.
     *
     * @param x            The value to generate the probability for.
     * @param elementProbs The probability of each category.
     * @return The probability of category x being produced by this distribution.
     */
    public static double logProbabilityCategorical(int x, double[] elementProbs) {
        if(x < 0 || x >= elementProbs.length)
            return Double.NEGATIVE_INFINITY;
        else
            return log(elementProbs[x]);
    }

    /**
     * Method to add the Categorical distribution described by to an array holding the cumulative distributions so far.
     * 
     * @param distribution The array holding the existing cumulative probabilities.
     * @param scale        A positive scale value that the added distribution should be scaled by.
     * @param elementProbs The probability of each test returning true.
     */
    public static void addProbabilityDistributionCategorical(double[] distribution, double scale,
            double[] elementProbs) {
        int length = elementProbs.length;
        for(int i = 0; i < length; i++)
            distribution[i] += scale * elementProbs[i];
    }

    // Cauchy
    /**
     * Method to sample a value from a Cauchy distribution.
     * 
     * @param rng      A random number generator for the sampling.
     * @param location The location parameter of the Cauchy distribution.
     * @param scale    The scale parameter of the Cauchy distribution.
     * @return The sampled value.
     */
    public static double sampleCauchy(Rng rng, double location, double scale) {
        double y = rng.uniform();
        return Math.tan((y - 0.5) * Math.PI) * scale + location;
    }

    /**
     * Method to calculate the probability of a value being drawn from a Cauchy distribution.
     * 
     * @param value    The value to calculate the probability of being drawn.
     * @param location The location parameter of the Cauchy distribution.
     * @param scale    The scale parameter of the Cauchy distribution.
     * @return The probability of drawing value from the described distribution.
     */
    public static double probabilityCauchy(double value, double location, double scale) {
        double t = (value - location) / scale;
        t = t * t;
        return 1 / (scale * Math.PI * (1 + t));
    }

    /**
     * Method to calculate the log probability of a value being drawn from a Cauchy distribution.
     * 
     * @param value    The value to calculate the probability of being drawn.
     * @param location The location parameter of the Cauchy distribution.
     * @param scale    The scale parameter of the Cauchy distribution.
     * @return The log probability of drawing value from the described distribution.
     */
    public static double logProbabilityCauchy(double value, double location, double scale) {
        return Math.log(probabilityCauchy(value, location, scale));
    }

    // Dirichlet
    /**
     * A method for populating an array output with Dirichlets drawn using beta
     * <a href= "https://en.wikipedia.org/wiki/Dirichlet_distribution#Rng_number_generation">
     * https://en.wikipedia.org/wiki/Dirichlet_distribution#Rng_number_generation</a>
     *
     * @param rng    A random number generator for the sampling.
     * @param beta   An array of values describing the Dirichlet distribution.
     * @param output The target output array.
     */
    public static void sampleDirichlet(Rng rng, double[] beta, double[] output) {
        assert beta.length == output.length : "Arrays must be the same length";
        double sum = 0;
        for(int i = 0; i < output.length; i++) {
            double v = sampleGamma(rng, beta[i]);
            output[i] = v;
            sum += v;
        }

        for(int i = 0; i < output.length; i++)
            output[i] /= sum;
    }

    /**
     * Method to calculate the probability of drawing a specific value from a Dirichlet distribution.
     * <a href="https://en.wikipedia.org/wiki/Dirichlet_distribution">
     * https://en.wikipedia.org/wiki/Dirichlet_distribution</a>
     *
     * @param value The value to calculate the probability of drawing.
     * @param beta  An array of values describing the Dirichlet distribution.
     * @return The probability of drawing the value from the described distribution.
     */
    public static double probabilityDirichlet(double[] value, double[] beta) {
        double prod = 1.0;
        double prodGamma = 1.0;
        double sumParam = 0.0;
        for(int i = 0; i < value.length; i++) {
            if(value[i] < 0 || value[i] > 1) {
                return 0.0;
            }
            // Collect data for the constant
            prodGamma *= Gamma.gamma(beta[i]);
            sumParam += beta[i];
            // Collect data for the sample
            prod *= pow(value[i], (beta[i] - 1));
        }
        // Construct and apply the constant.
        prod /= prodGamma;
        prod *= Gamma.gamma(sumParam);
        return prod;
    }

    /**
     * Method to calculate the log probability of drawing a specific value from a Dirichlet distribution.
     * <a href="https://en.wikipedia.org/wiki/Dirichlet_distribution">
     * https://en.wikipedia.org/wiki/Dirichlet_distribution</a>
     *
     * @param value The value to calculate the probability of drawing.
     * @param beta  An array of values describing the Dirichlet distribution.
     * @return The log probability of drawing the value from the described distribution.
     */
    public static double logProbabilityDirichlet(double[] value, double[] beta) {
        double sum = 0.0;
        double sumParam = 0.0;

        for(int i = 0; i < value.length; i++) {
            if(value[i] < 0 || value[i] > 1) {
                return Double.MIN_VALUE;
            }

            // Collect data for the constant
            sum -= Gamma.logGamma(beta[i]);
            sumParam += beta[i];

            sum += log(value[i]) * (beta[i] - 1);
        }

        // Construct and apply the constant.
        sum += Gamma.logGamma(sumParam);

        return sum;
    }

    // Exponential
    /**
     * Method to sample a value from an Exponential distribution.
     * 
     * @param rng    A random number generator for the sampling.
     * @param lambda The lambda parameter of the distribution.
     * @return The sampled value.
     */
    public static double sampleExponential(Rng rng, double lambda) {
        return rng.exponential() / lambda;
    }

    /**
     * Method to calculate the probability of a value being drawn from an Exponential distribution.
     * 
     * @param value  The value to calculate the probability of being drawn.
     * @param lambda The lambda parameter of the Exponential distribution.
     * @return The probability of drawing value from the described distribution.
     */
    public static double probabilityExponential(double value, double lambda) {
        if(value < 0 || value == Double.POSITIVE_INFINITY)
            return 0;
        else
            return lambda * Math.exp(-lambda * value);
    }

    /**
     * Method to calculate the log probability of a value being drawn from an Exponential distribution.
     * 
     * @param value  The value to calculate the probability of being drawn.
     * @param lambda The lambda parameter of the Exponential distribution.
     * @return The log probability of drawing value from the described distribution.
     */
    public static double logProbabilityExponential(double value, double lambda) {
        if(value < 0 || value == Double.POSITIVE_INFINITY)
            return Double.NEGATIVE_INFINITY;
        else
            return Math.log(lambda) - lambda * value;
    }

    // Gamma
    /**
     * Method to sample a Gamma distribution assuming that beta is 1
     *
     * @param rng   A random number generator for the sampling.
     * @param alpha The parameter alpha for the Gamma distribution.
     * @return The value sampled from the described Gamma distribution.
     */
    static double sampleGamma(Rng rng, double alpha) { // alpha > 0, beta implicitly 1.
        boolean aflag = false;
        if(alpha < 1) {
            aflag = true;
            alpha += 1;
        }

        double gamma = sampleGammaAux(rng, alpha);

        if(aflag)
            gamma *= pow(rng.uniform(), 1.0 / (alpha - 1));

        return gamma;
    }

    /**
     * Method to sample a value from a Gamma distribution.
     * <a href= "https://en.wikipedia.org/wiki/Gamma_distribution#Generating_gamma-distributed_Rng_variables">
     * https://en.wikipedia.org/wiki/Gamma_distribution#Generating_gamma-distributed_Rng_variables</a>
     *
     * @param rng   A random number generator for the sampling.
     * @param alpha The alpha parameter of the Gamma distribution.
     * @param beta  The beta parameter of the Gamma distribution.
     * @return The value sampled from the described distribution.
     */
    public static double sampleGamma(Rng rng, double alpha, double beta) { // scaling for beta
        return sampleGamma(rng, alpha) / beta;
    }

    /**
     * Auxiliary method used when sampling Gamma distributions. A version of Ahrens-Dieter acceptance–rejection method
     * <a href= "https://en.wikipedia.org/wiki/Gamma_distribution#Generating_gamma-distributed_Rng_variables">
     * https://en.wikipedia.org/wiki/Gamma_distribution#Generating_gamma-distributed_Rng_variables</a>
     *
     * @param rng   A random number generator for the sampling.
     * @param alpha The alpha parameter of the Gamma distribution.
     * @return The calculated value.
     */
    private static double sampleGammaAux(Rng rng, double alpha) { // alpha > 0
        assert (alpha > 0);
        double d = alpha - (1.0 / 3.0);
        double c = 1.0 / sqrt(9 * d);

        while(true) {
            double x = rng.normal(0, 1);
            double v = (1 + c * x);
            v = v * v * v; // v=v^3

            if(v > 0) {
                double u = rng.uniform();
                double xsq = x * x;
                // This guard doesn't appear in the wikipedia article, but is in the paper to avoid the 2 log calls.
                if(u < 1 - 0.0331 * xsq * xsq) {
                    return d * v;
                } else if(log(u) < 0.5 * xsq + d * (1 - v + log(v))) {
                    return d * v;
                }
            }
        }
    }

    /**
     * Calculate the probability of x being sampled from a Gamma distribution described by alpha and beta. With a shape
     * parameter α = k and an inverse scale parameter β = 1/θ, called a rate parameter.
     *
     * @param x     The value to calculate the probability for.
     * @param alpha The alpha parameter of the Gamma distribution.
     * @param beta  The beta parameter of the Gamma distribution.
     * @return The probability of the described distribution producing the value x.
     */
    public static double probabilityGamma(double x, double alpha, double beta) {
        if(x <= 0)
            return 0;
        else
            return (pow(beta, alpha) / Gamma.gamma(alpha)) * pow(x, alpha - 1) * exp(-beta * x);
    }

    /**
     * Calculate the log probability of x being sampled from a Gamma distribution described by alpha and beta. With a
     * shape parameter α = k and an inverse scale parameter β = 1/θ, called a rate parameter.
     *
     * @param x     The value to calculate the probability for.
     * @param alpha The alpha parameter of the Gamma distribution.
     * @param beta  The beta parameter of the Gamma distribution.
     * @return The log probability of the described distribution producing the value x.
     */
    public static double logProbabilityGamma(double x, double alpha, double beta) {
        if(x <= 0)
            return Double.NEGATIVE_INFINITY;
        else
            /* Normalising constant + probability. */
            return alpha * log(beta) - Gamma.logGamma(alpha) + (alpha - 1) * log(x) - beta * x;
    }

    // Gaussian
    /**
     * Method to sample from a Gaussian distribution.
     * 
     * @param rng      A random number generator for the sampling.
     * @param mu       The mean of the Gaussian distribution.
     * @param variance The variance of the Gaussian distribution.
     * @return The value sampled from the Gaussian distribution.
     */
    public static double sampleGaussian(Rng rng, double mu, double variance) {
        return mu + rng.normal(0, variance);
    }

    /**
     * Method to calculate the probability of x appearing in the described Gaussian distribution.
     *
     * @param x        The value to calculate the probability of generating.
     * @param mu       The mean of the Gaussian distribution.
     * @param variance The variance of the Gaussian distribution.
     * @return The probability of the described distribution generating the value x.
     */
    public static double probabilityGaussian(double x, double mu, double variance) {
        double xMu = x - mu;
        double xMu2 = xMu * xMu;
        return 1.0 / sqrt(2.0 * PI * variance) * exp(-xMu2 / (2.0 * variance));
    }

    /**
     * Method to calculate the log probability of x appearing in the described Gaussian distribution.
     *
     * @param x        The value to calculate the probability of generating.
     * @param mu       The mean of the Gaussian distribution.
     * @param variance The variance of the Gaussian distribution.
     * @return The log probability of the described distribution generating the value x.
     */
    public static double logProbabilityGaussian(double x, double mu, double variance) {
        double xMu = x - mu;
        double xMu2 = xMu * xMu;
        return -0.5 * log(2.0 * PI * variance) + (-xMu2 / (2.0 * variance));
    }

    // Half Cauchy
    /**
     * Method to sample a value form a Half Cauchy distribution.
     * 
     * @param rng      A random number generator for the sampling.
     * @param location The location parameter of the distribution.
     * @param scale    The scale parameter of the distribution.
     * @return The value sampled from the distribution.
     */
    public static double sampleHalfCauchy(Rng rng, double location, double scale) {
        return Math.abs(sampleCauchy(rng, location, scale));
    }

    /**
     * Method to calculate the probability of a value being drawn from the described Half Cauchy distribution.
     * 
     * @param value    The value to calculate the probability of being drawn.
     * @param location The location parameter of the distribution.
     * @param scale    The scale parameter of the distribution.
     * @return The probability of drawing the value from the described distribution.
     */
    public static double probabilityHalfCauchy(double value, double location, double scale) {
        if(value < location)
            return 0;
        else
            return 2 * probabilityCauchy(value, location, scale);
    }

    /**
     * Method to calculate the probability of a value being drawn from the described Half Cauchy distribution.
     * 
     * @param value    The value to calculate the probability of being drawn.
     * @param location The location parameter of the distribution.
     * @param scale    The scale parameter of the distribution.
     * @return The probability of drawing the value from the described distribution.
     */
    public static double logProbabilityHalfCauchy(double value, double location, double scale) {
        return Math.log(probabilityHalfCauchy(value, location, scale));
    }

    // Inverse Gamma
    /**
     * Method to sample a value from an inverse gama distribution.
     * <a href= "https://en.wikipedia.org/wiki/Inverse-gamma_distribution">
     * https://en.wikipedia.org/wiki/Inverse-gamma_distribution</a>
     *
     * @param rng   A random number generator for the sampling.
     * @param alpha The alpha parameter of the distribution.
     * @param beta  The beta parameter of the distribution.
     * @return The value sampled from the distribution.
     */
    public static double sampleInverseGamma(Rng rng, double alpha, double beta) {
        return beta / sampleGamma(rng, alpha);
    }

    /**
     * Method to calculate the probability of value from an inverse gamma.
     * <a href= "https://en.wikipedia.org/wiki/Inverse-gamma_distribution">
     * https://en.wikipedia.org/wiki/Inverse-gamma_distribution</a>
     *
     * @param value The value to calculate the probability of distribution returning.
     * @param alpha The alpha parameter of the distribution.
     * @param beta  The beta parameter of the distribution.
     * @return The probability of sampling the value from the described distribution.
     */
    public static double probabilityInverseGamma(double value, double alpha, double beta) {
        if(value <= 0)
            return 0;
        else
            return (pow(beta, alpha) / Gamma.gamma(alpha)) * pow(value, -(alpha - 1)) * exp(-beta / value);
    }

    /**
     * Method to calculate the log probability of value from an inverse gamma.
     * <a href= "https://en.wikipedia.org/wiki/Inverse-gamma_distribution">
     * https://en.wikipedia.org/wiki/Inverse-gamma_distribution</a>
     *
     * @param value The value to calculate the probability of distribution returning.
     * @param alpha The alpha parameter of the distribution.
     * @param beta  The beta parameter of the distribution.
     * @return The log probability of sampling the value from the described distribution.
     */
    public static double logProbabilityInverseGamma(double value, double alpha, double beta) {
        if(value <= 0)
            return Double.NEGATIVE_INFINITY;
        else
            return alpha * log(beta) - Gamma.logGamma(alpha) - (alpha - 1) * log(value) - beta / value;
    }

    // Poisson
    /**
     * A method to sample a value from a Poisson distribution.
     * 
     * @param rng  A random number generator for the sampling.
     * @param rate The rate parameter of the Poisson distribution.
     * @return The value sampled from the described distribution.
     */
    public static int samplePoisson(Rng rng, double rate) {
        if(rate <= 30) { // For small sizes use Kunths algorithm.
            double p = 1;
            int n = 0;
            double limit = exp(-rate);
            do {
                n++;
                p *= rng.uniform();
            } while(p > limit);
            return n - 1;
        } else { // Use rejection sampling for bigger sizes. TODO look at pushing some
            // of this calculation into the core model so repeated calculations can be
            // avoided.
            double c = 0.767 - 3.36 / rate;
            double beta = PI / sqrt(3.0 * rate);
            double alpha = beta * rate;
            double k = log(c) - rate - log(beta);

            while(true) {
                double u = rng.uniform();
                double x = (alpha - log((1.0 - u) / u)) / beta;
                int n = (int) floor(x + 0.5);
                if(n >= 0) {
                    double v = rng.uniform();
                    double y = alpha - beta * x;
                    double z = (1.0 + exp(y));
                    double lhs = y + log(v / (z * z));
                    double rhs = k + n * log(rate) - ApproximateFactorial.approxLogFac(n);
                    if(lhs <= rhs)
                        return n;
                }
            }
        }
    }

    // Multinomial
    /**
     * Method to sample a value from Multinomial distribution.
     * 
     * @param rng          A random number generator for the sampling.
     * @param elementProbs The probability of returning each possible value from the categorical distribution.
     * @param n            The number of tests to include in the output
     * @param output       The array to hold the results of the sampling.
     */
    public static void sampleMultinomial(Rng rng, double[] elementProbs, int n, int[] output) {
        Arrays.fill(output, 0);

        double l = elementProbs.length;
        for(int i = 0; i < n; i++) {
            // Scale a uniform value to pick a category.
            double val = rng.uniform();

            // Loop through to find the category this value matches with.
            double sum = 0;
            int j;
            for(j = 0; j < l; j++) {
                sum += elementProbs[j];
                if(sum >= val) {
                    output[j]++;
                    break;
                }
            }
            if(j == l)
                throw new SandwoodException("This should be unreachable");
        }
    }

    /**
     * Calculate the probability of a multinomial producing the sample x.
     * 
     * @param xs           The value to generate the probability of sampling.
     * @param elementProbs The probability of each category.
     * @param n            The total number of values sampled in the distribution.
     * @return The probability of x being produced by this distribution.
     */
    public static double probabilityMultinomial(int[] xs, double[] elementProbs, int n) {
        return Math.exp(logProbabilityMultinomial(xs, elementProbs, n));
    }

    /**
     * Calculate the log probability of a multinomial producing the sample x.
     * 
     * @param xs           The value to generate the probability of sampling.
     * @param elementProbs The probability of each category.
     * @param n            The total number of values sampled in the distribution.
     * @return The probability of x being produced by this distribution.
     */
    public static double logProbabilityMultinomial(int[] xs, double[] elementProbs, int n) {
        int l = xs.length;
        // Test that the arrays are the correct lengths;
        if(elementProbs.length != l)
            return Double.NEGATIVE_INFINITY;

        double p = 0;
        double count = 0;
        for(int i = 0; i < l; i++) {
            int x = xs[i];
            if(x != 0) {
                count += x;
                p += x * Math.log(elementProbs[i]);
                p -= ApproximateFactorial.approxLogFac(x);
            }
        }
        // Check the correct number of events occured.
        if(count != n)
            return Double.NEGATIVE_INFINITY;

        p += ApproximateFactorial.approxLogFac(n);
        return p;
    }

    /**
     * Method to calculate the probability of value being generated by the described Poisson distribution.
     * 
     * @param value The value to calculate the probability of sampling.
     * @param rate  The rate parameter of the distribution.
     * @return The probability of sampling value from the described distribution.
     */
    public static double probabilityPoisson(int value, double rate) {
        return pow(E, logProbabilityPoisson(value, rate));
    }

    /**
     * Method to calculate the log probability of value being generated by the described Poisson distribution.
     * 
     * @param value The value to calculate the probability of sampling.
     * @param rate  The rate parameter of the distribution.
     * @return The log probability of sampling value from the described distribution.
     */
    public static double logProbabilityPoisson(int value, double rate) {
        if(value < 0 || rate <= 0)
            return Double.NEGATIVE_INFINITY;
        double logFac = ApproximateFactorial.approxLogFac(value);
        return value * log(rate) - rate - logFac;
    }

    // StudentT
    /*
     * based on algorithm from https://doi.org/10.2307/2153537 This should use 2 independent rng's, but as that is not
     * an option, we are going to rely on the higher quality of modern rngs.
     */
    /**
     * Method to draw a sample value from a studentT distribution.
     * 
     * @param rng A random number generator for the sampling.
     * @param v   The StudentT parameter v.
     * @return The parameter drawn from the described distribution.
     */
    public static double sampleStudentT(Rng rng, double v) {
        double a, b, w; // a is U and b is V
        do {
            a = rng.uniform();
            a = 2 * a - 1;
            b = rng.uniform();
            b = 2 * b - 1;
            a = a * a;
            w = a + b * b;
        } while(w > 1);

        double c = a / w;
        double r = v * (Math.pow(w, -2 / v) - 1);
        return Math.sqrt(c * r);
    }

    // function from
    // https://en.wikipedia.org/wiki/Student%27s_t-distribution#Probability_density_function
    /**
     * Method to calculate the probability of sampling value from the described StudentT distribution.
     * 
     * @param value The value to calculate the probability of sampling.
     * @param v     The v parameter of the StudentT distribution.
     * @return The probability of sampling the value from the described distribution.
     */
    public static double probabilityStudentT(double value, double v) {
        if(v <= 0)
            return 0;
        else {
            double num = Math.pow(1 + value * value / v, -(v + 1) / 2);
            double v2 = v / 2;
            double denum = Math.sqrt(v) * Gamma.gamma(0.5) * Gamma.gamma(v2) / Gamma.gamma(0.5 + v2);
            return num / denum;
        }
    }

    /**
     * Method to calculate the probability of sampling value from the described StudentT distribution.
     * 
     * @param value The value to calculate the probability of sampling.
     * @param v     The v parameter of the StudentT distribution.
     * @return The probability of sampling the value from the described distribution.
     */
    public static double logProbabilityStudentT(double value, double v) {
        if(v <= 0)
            return 0;
        else {
            double num = -((v + 1) / 2) * log(1 + value * value / v);
            double v2 = v / 2;
            double denum = 0.5 * Math.log(v) + Gamma.logGamma(0.5) + Gamma.logGamma(v2) - Gamma.logGamma(0.5 + v2);
            return num - denum;
        }
    }

    // Uniform
    /**
     * Method to sample a value from a uniform distribution.
     * 
     * @param rng   A random number generator for the sampling.
     * @param start The value the uniform distribution starts at.
     * @param end   The value the uniform distribution ends at.
     * @return The value sampled from the distribution.
     */
    public static double sampleUniform(Rng rng, double start, double end) {
        return rng.uniform() * (end - start) + start;
    }

    /**
     * Method to calculate the probability of a value being sampled from a Uniform distribution.
     *
     * @param value The value to calculate the probability of sampling.
     * @param start The start of the uniform distribution.
     * @param end   The end of the distribution.
     * @return The probability of the value being sampled from the described distribution.
     */
    public static double probabilityUniform(double value, double start, double end) {
        if(value >= start && value <= end)
            return 1 / (end - start);
        else
            return 0;
    }

    /**
     * Method to calculate the log probability of a value being sampled from a Uniform distribution.
     *
     * @param value The value to calculate the probability of sampling.
     * @param start The start of the uniform distribution.
     * @param end   The end of the distribution.
     * @return The log probability of the value being sampled from the described distribution.
     */
    public static double logProbabilityUniform(double value, double start, double end) {
        if(value >= start && value <= end)
            return -log(end - start);
        else
            return Double.NEGATIVE_INFINITY;
    }
}