/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import java.io.IOException;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.json.JsonDecoder;
import org.sandwood.runtime.internal.json.JsonEncoder;
import org.sandwood.runtime.internal.model.util.Allocator;
import org.sandwood.runtime.internal.model.util.BaseType;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedObjectArray;

public abstract class ComputedObjectArrayInternal<A> extends ComputedVariableInternal
        implements ComputedObjectArray<A> {

    private A[][] samples;
    private A[] map = null;

    private final int arrayDimension;
    private final BaseType baseType;

    public ComputedObjectArrayInternal(Model model, String name, boolean isSample, BaseType baseType,
            int arrayDimension) {
        super(model, name, isSample);
        this.baseType = baseType;
        this.arrayDimension = arrayDimension;
    }

    @Override
    public final A[][] getSamples() {
        synchronized(model) {
            return samples;
        }
    }

    @Override
    public final A[] getMAP() {
        synchronized(model) {
            return map;
        }
    }

    @Override
    public final void initializeSamples(int iterations) {
        samples = constructArray(iterations);
        i = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void ingestSample() {
        A[] v = getValue();
        samples[i++] = (A[]) copy(v, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void ingestMap() {
        A[] v = getValue();
        if(map == null)
            map = (A[]) copy(v, 0);
        else
            copy(map, v);
    }

    // TODO update copy to copy with the correct allocation of arrays.
    private void copy(Object[] t, Object[] v) {
        if(v[0] instanceof double[]) {
            for(int j = 0; j < v.length; j++) {
                double[] vj = (double[]) v[j];
                double[] tj = (double[]) t[j];
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
        } else if(v[0] instanceof int[]) {
            for(int j = 0; j < v.length; j++) {
                int[] vj = (int[]) v[j];
                int[] tj = (int[]) t[j];
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
        } else if(v[0] instanceof boolean[]) {
            for(int j = 0; j < v.length; j++) {
                boolean[] vj = (boolean[]) v[j];
                boolean[] tj = (boolean[]) t[j];
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
        } else if(v[0] instanceof Object[]) {
            for(int j = 0; j < v.length; j++) {
                copy((Object[]) t[j], (Object[]) v[j]);
            }
        } else {
            throw new SandwoodException("Unknown type: " + v[0].getClass().getCanonicalName() + " in variable " + name);
        }
    }

    private Object[] copy(Object[] v, int depth) {
        int copyDimension = arrayDimension - depth;
        if(copyDimension > 2) {
            Object[] t = Allocator.allocate(baseType, v.length, copyDimension);
            for(int j = 0; j < v.length; j++) {
                t[j] = copy((Object[]) v[j], depth + 1);
            }
            return t;
        } else if(baseType == BaseType.DOUBLE) {
            double[][] dd = new double[v.length][];
            for(int j = 0; j < v.length; j++) {
                double[] vj = (double[]) v[j];
                double[] tj = new double[vj.length];
                dd[j] = tj;
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
            return dd;
        } else if(baseType == BaseType.INT) {
            int[][] ii = new int[v.length][];
            for(int j = 0; j < v.length; j++) {
                int[] vj = (int[]) v[j];
                int[] tj = new int[vj.length];
                ii[j] = tj;
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
            return ii;
        } else if(baseType == BaseType.BOOLEAN) {
            boolean[][] bb = new boolean[v.length][];
            for(int j = 0; j < v.length; j++) {
                boolean[] vj = (boolean[]) v[j];
                boolean[] tj = new boolean[vj.length];
                bb[j] = tj;
                System.arraycopy(vj, 0, tj, 0, vj.length);
            }
            return bb;
        } else {
            String type = baseType.name();
            for(int i = 0; i < arrayDimension; i++)
                type = type + "[]";
            throw new SandwoodException("Unknown type: " + type + " in variable " + name);
        }
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
        map = decoder.getObjectArray(baseType, arrayDimension);
        setValueInternal(map);
    }

    @Override
    public final void setValue(A[] value) {
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

    protected abstract void setValueInternal(A[] value);

    public abstract A[][] constructArray(int iterations);
}
