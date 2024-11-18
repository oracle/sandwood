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
class CoinBiasEstimatorMK2 {

    public static void main(String[] args) {
        boolean[] observedFlips = { false, false, false, false, false, false, false, false, false, false, true, true,
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
                true };
        CoinModel coinModel = new CoinModel();

        coinModel.observedFlips.setValue(observedFlips);
        coinModel.bias.setRetentionPolicy(RetentionPolicy.MAP);

        coinModel.inferValues(1000);
        System.out.println("bias " + coinModel.bias.getMAP());

        coinModel.close();
    }
}
