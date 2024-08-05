/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.examples.hmm;

import org.sandwood.runtime.model.RetentionPolicy;

/**
 * Class demonstrating the use of an HMM model to model the tossing of a number of biased coins.
 */

class HMM_Flips_Demo {
    public static void main(String[] args) {
        boolean[] observedFlips = { false, false, false, false, false, false, false, false, false, false, true, true,
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
                true };
        int noCoins = 2;
        HMM_Flips coinModel = new HMM_Flips(observedFlips, noCoins);

        coinModel.setDefaultRetentionPolicy(RetentionPolicy.MAP);

        coinModel.setBurnin(10000);
        coinModel.setThinning(3);

        coinModel.inferValues(10000);
        System.out.print("bias");
        for(double bias:coinModel.bias.getMAP())
            System.out.print(" " + bias);
        System.out.println();

        System.out.println("States");
        for(int coin:coinModel.st.getMAP()) {
            System.out.println("\t" + coin);
        }

        coinModel.close();
    }
}
