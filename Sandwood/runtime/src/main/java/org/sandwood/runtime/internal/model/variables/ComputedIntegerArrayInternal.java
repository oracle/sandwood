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
import org.sandwood.runtime.model.variables.ComputedIntegerArray;

public abstract class ComputedIntegerArrayInternal extends ComputedVariableInternal implements ComputedIntegerArray {
    private int[][] samples;
    private int[] map = null;

    public ComputedIntegerArrayInternal(Model model, String name, boolean isSample) {
        super(model, name, isSample);
    }

    @Override
    public final int[][] getSamples() {
        synchronized(model) {
            return samples;
        }
    }

    @Override
    public final int[] getMAP() {
        synchronized(model) {
            return map;
        }
    }

    @Override
    public final void initializeSamples(int iterations) {
        samples = new int[iterations][];
        i = 0;
    }

    @Override
    public final void ingestSample() {
        samples[i++] = getValue().clone();
    }

    @Override
    public final void ingestMap() {
        int[] v = getValue();
        if(map == null)
            map = new int[v.length];
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
        map = decoder.getIntArray();
        setValueInternal(map);
    }

    @Override
    public final void setValue(int[] value) {
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

    protected abstract void setValueInternal(int[] value);
}
