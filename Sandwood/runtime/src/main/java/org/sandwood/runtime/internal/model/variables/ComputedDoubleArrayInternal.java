/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import java.io.IOException;

import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.json.JsonDecoder;
import org.sandwood.runtime.internal.json.JsonEncoder;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedDoubleArray;

public abstract class ComputedDoubleArrayInternal extends ComputedVariableInternal implements ComputedDoubleArray {
    private double[][] samples;
    private double[] map = null;

    public ComputedDoubleArrayInternal(Model model, String name, boolean isSample) {
        super(model, name, isSample);
    }

    @Override
    public final double[][] getSamples() {
        synchronized(model) {
            return samples;
        }
    }

    @Override
    public final double[] getMAP() {
        synchronized(model) {
            return map;
        }
    }

    @Override
    public final void initializeSamples(int iterations) {
        samples = new double[iterations][];
        i = 0;
    }

    @Override
    public final void ingestSample() {
        samples[i++] = getValue().clone();
    }

    @Override
    public final void ingestMap() {
        double[] v = getValue();
        if(map == null)
            map = new double[v.length];
        System.arraycopy(v, 0, map, 0, v.length);
    }

    @Override
    public final void toJson(JsonEncoder e) throws IOException {
        switch(p) {
            case MAP:
                e.addObject(name(), map);
                break;
            case NONE:
                break;
            case SAMPLE:
                e.addObject(name(), samples);
                break;
        }
    }

    @Override
    public final void fromJSON(JsonDecoder decoder) throws SandwoodJsonException, IOException {
        p = RetentionPolicy.MAP;
        map = decoder.getDoubleArray();
        setValueInternal(map);
    }

    @Override
    public final void setValue(double[] value) {
        synchronized(model) {
            testSettable();
            p = RetentionPolicy.NONE;
            map = value;
            setValueInternal(map);
        }
    }

    @Override
    public final boolean setToMAPValue() {
        synchronized(model) {
            if(p == RetentionPolicy.MAP && valueComputed()) {
                setValue(getMAP());
                return true;
            } else
                return false;
        }
    }

    protected abstract void setValueInternal(double[] value);
}
