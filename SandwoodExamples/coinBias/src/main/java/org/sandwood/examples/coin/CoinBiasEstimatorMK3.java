/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.examples.coin;

import org.sandwood.runtime.model.RetentionPolicy;

/**
 * Given observed flips of a coin, determine if the coin is biased. Uses the basic CoinModel which assumes the coin is
 * fair (uniform beta prior) and can be modelled with a Bernoulli likelihood distribution.
 */
class CoinBiasEstimatorMK3 {

    public static void main(String[] args) {
        boolean[] observedFlips = { false, true };
        CoinModel coinModel = new CoinModel();

        CoinModel.AllInputs inputs = new CoinModel.AllInputs(observedFlips);

        coinModel.bias.setRetentionPolicy(RetentionPolicy.MAP);

        CoinModel.InferredModelOutputs result = coinModel.inferValues(1, inputs);

        System.out.println("bias " + result.bias[0]);
        
        coinModel.close();
    }
}
