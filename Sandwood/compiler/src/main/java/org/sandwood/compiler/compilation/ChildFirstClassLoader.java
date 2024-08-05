/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Based on the work in
 * https://medium.com/@isuru89/java-a-child-first-class-loader-cbd9c3d0305
 */
public class ChildFirstClassLoader extends URLClassLoader {

    public ChildFirstClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * Loads the class with the specified <a href="#binary-name">binary name</a>. This method searches for classes in
     * the same manner as the {@link #loadClass(String, boolean)} method. It is invoked by the Java virtual machine to
     * resolve class references. Invoking this method is equivalent to invoking {@link #loadClass(String, boolean)
     * loadClass(name, false)}.
     *
     * @param name The <a href="#binary-name">binary name</a> of the class
     *
     * @return The resulting {@code Class} object
     *
     * @throws ClassNotFoundException If the class was not found
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // has the class loaded already?
        Class<?> loadedClass = findLoadedClass(name);
        if(loadedClass == null) {
            try {
                // find the class from given jar urls as in first constructor parameter.
                loadedClass = findClass(name);
            } catch(ClassNotFoundException e) {
                // class is not found in the given urls.
                // Let's try it in parent classloader.
                // If class is still not found, then this method will throw class not found ex.
                loadedClass = super.loadClass(name, resolve);
            }
        }

        if(resolve) { // marked to resolve
            resolveClass(loadedClass);
        }
        return loadedClass;
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        List<URL> allRes = new LinkedList<>();

        // load resource from this classloader
        Enumeration<URL> thisRes = findResources(name);
        if(thisRes != null) {
            while(thisRes.hasMoreElements()) {
                allRes.add(thisRes.nextElement());
            }
        }

        // then try finding resources from parent classloaders
        Enumeration<URL> parentRes = super.findResources(name);
        if(parentRes != null) {
            while(parentRes.hasMoreElements()) {
                allRes.add(parentRes.nextElement());
            }
        }

        return new Enumeration<>() {
            private final Iterator<URL> it = allRes.iterator();

            @Override
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override
            public URL nextElement() {
                return it.next();
            }
        };
    }

    @Override
    public URL getResource(String name) {
        URL res = findResource(name);
        if(res == null) {
            res = super.getResource(name);
        }
        return res;
    }
}