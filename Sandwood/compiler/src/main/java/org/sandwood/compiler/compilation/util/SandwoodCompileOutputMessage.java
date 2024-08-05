/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class SandwoodCompileOutputMessage implements Comparable<SandwoodCompileOutputMessage> {
    public final String file;
    public final Location loc;
    public final String source;
    public final String message;

    private static final int maxCols = 100;

    public SandwoodCompileOutputMessage(String file, Location loc, String source, String message) {
        this.file = file;
        this.loc = loc;
        this.message = message;
        this.source = source;
    }

    public SandwoodCompileOutputMessage(String file, String message) {
        this.file = file;
        this.loc = null;
        this.message = message;
        this.source = null;
    }

    public SandwoodCompileOutputMessage(String message) {
        this.file = null;
        this.loc = null;
        this.message = message;
        this.source = null;
    }

    protected abstract String messageType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(file != null) {
            sb.append(file + "\n");
            if(loc != null) {
                if(loc.startLine == loc.endLine) {
                    if(loc.startCol == loc.endCol) {
                        sb.append(messageType() + " on line: " + loc.startLine + " at column " + loc.startCol + "\n");
                    } else {
                        sb.append(messageType() + " on line: " + loc.startLine + " between columns " + loc.startCol
                                + " and " + loc.endCol + "\n");
                    }
                } else {
                    sb.append(messageType() + " between lines: " + loc.startLine + " column " + loc.startCol
                            + " and line " + loc.endLine + " column " + loc.endCol + "\n");
                }

                for(String line:message.split("\n")) {
                    String[] words = line.split(" ");
                    int cols = 0;
                    for(String word:words) {
                        int length = word.length();
                        if(cols == 0) {
                            sb.append(word);
                            cols += length;
                        } else if(cols + length + 1 < maxCols) {
                            sb.append(" " + word);
                            cols += 1 + length;
                        } else {
                            sb.append("\n" + word);
                            cols = length;
                        }
                    }
                    sb.append("\n");
                }
                sb.append("\n");

                if(source != null)
                    sb.append("\"" + source + "\"\n");
            } else {
                sb.append(message + "\n");
            }
        } else {
            sb.append("General " + messageType() + "\n");
            sb.append(message + "\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(SandwoodCompileOutputMessage o) {
        if(file == null) {
            if(o.file == null)
                return message.compareTo(o.message);
            else
                return 1;
        } else {// Both files are the same.
            if(o.file == null)
                return -1;
            else {
                int i = file.compareTo(o.file);
                if(i != 0)
                    return i;
                if(loc == null) {
                    if(o.loc == null)// Both locations are null
                        return message.compareTo(o.message);
                    else
                        return 1;
                } else {
                    if(o.loc == null)
                        return -1;
                    else {// Both locations are set.
                        i = loc.compareTo(o.loc);
                        if(i != 0)
                            return i;
                        else
                            return message.compareTo(o.message);
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        result = prime * result + ((loc == null) ? 0 : loc.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        SandwoodCompileOutputMessage other = (SandwoodCompileOutputMessage) obj;
        if(file == null) {
            if(other.file != null)
                return false;
        } else if(!file.equals(other.file))
            return false;
        if(loc == null) {
            if(other.loc != null)
                return false;
        } else if(!loc.equals(other.loc))
            return false;
        if(message == null) {
            if(other.message != null)
                return false;
        } else if(!message.equals(other.message))
            return false;
        if(source == null) {
            return other.source == null;
        } else
            return source.equals(other.source);
    }
}
